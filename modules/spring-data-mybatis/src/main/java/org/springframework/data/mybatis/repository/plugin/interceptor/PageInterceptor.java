package org.springframework.data.mybatis.repository.plugin.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mybatis.repository.plugin.pagination.PaginationExecutor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor {

    public static final ThreadLocal<Pageable> PAGE_PARAM = new ThreadLocal<>();
    public static final ThreadLocal<Long> PAGINATION_TOTAL = new ThreadLocal<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept before");
        Pageable pageable = PAGE_PARAM.get();
        if(pageable != null) {
            StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
            MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
            MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
            BoundSql boundSql = statementHandler.getBoundSql();
            // 原始的SQL语句
            String sql = boundSql.getSql();
            // 查询总条数的SQL语句
            String countSql = "select count(1) from (" + sql + ")a";
            Connection connection = (Connection)invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();
            if(rs.next()) {
                PAGINATION_TOTAL.set(rs.getLong(1));
            }
            // 排序
            Sort sort = pageable.getSort();
            if(sort != null) {
                Iterator<Sort.Order> iterator = sort.iterator();
                String orderBy = " ORDER BY ";
                while (iterator.hasNext()) {
                    Sort.Order order = iterator.next();
                    Sort.Direction direction = order.getDirection();
                    String property = order.getProperty();
                    orderBy += property + " " + direction;
                    if(iterator.hasNext())
                        orderBy += " , ";
                }
                sql += orderBy;
            }

            // 改造后带分页查询的SQL语句
            String pageSql = sql + " limit " + ((pageable.getPageNumber() < 0 ? 0 : pageable.getPageNumber()) * pageable.getPageSize())  + "," + pageable.getPageSize();
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }

        Object result = invocation.proceed();
        System.out.println("intercept after");
        return result;
    }


    @Override
    public Object plugin(Object target) {
        if (Executor.class.isAssignableFrom(target.getClass())) {
            return Plugin.wrap(new PaginationExecutor((Executor) target), this);
        }
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
