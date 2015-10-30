package org.springframework.data.mybatis.repository.query;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mybatis.repository.plugin.interceptor.PageInterceptor;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Binds spring Repository methods with mybatis mapper
 */
public class MybatisRepositoryQuery extends MapperMethod implements RepositoryQuery {

    private final MyBatisQueryMethod queryMethod;
    private final SqlSessionTemplate sessionTemplate;
    private final SqlCommand command;
    private final MethodSignature method;

    public MybatisRepositoryQuery(MyBatisQueryMethod queryMethod, SqlSessionTemplate sessionTemplate) {
        super(queryMethod.getRepositoryInterface(), queryMethod.getMethod(), sessionTemplate.getConfiguration());
        Assert.notNull(queryMethod);
        Assert.notNull(sessionTemplate);
        this.queryMethod = queryMethod;
        this.sessionTemplate = sessionTemplate;
        this.command = new SqlCommand(sessionTemplate.getConfiguration(), queryMethod.getRepositoryInterface(), queryMethod.getMethod());
        this.method = new MethodSignature(sessionTemplate.getConfiguration(), queryMethod.getMethod());
    }

    @Override
    public Object execute(Object[] parameters) {
//        if (queryMethod.getMethod().getReturnType() == Page.class) {
//            return executeForMany(sessionTemplate, parameters);
//        }
        return ReflectionUtils
                .invokeMethod(queryMethod.getMethod(), sessionTemplate.getMapper(queryMethod.getRepositoryInterface()), parameters);
    }

    private <E> Object executeForMany(SqlSession sqlSession, Object[] args) {
        List<E> result;
        PageInterceptor.PAGE_PARAM.set((Pageable) args[0]);
        Object param = method.convertArgsToSqlCommandParam(args);
        if (method.hasRowBounds()) {
            RowBounds rowBounds = method.extractRowBounds(args);
            result = sqlSession.<E>selectList(command.getName(), param, rowBounds);
        } else {
            result = sqlSession.<E>selectList(command.getName(), param);
        }
        Page<E> page =  new PageImpl<E>(result, (Pageable) args[0], PageInterceptor.PAGINATION_TOTAL.get());
        PageInterceptor.PAGE_PARAM.remove();
        PageInterceptor.PAGINATION_TOTAL.remove();
        return page;
    }

    @Override
    public QueryMethod getQueryMethod() {
        return queryMethod;
    }
}
