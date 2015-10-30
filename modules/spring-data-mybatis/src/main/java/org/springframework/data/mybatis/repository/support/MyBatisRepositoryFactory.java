package org.springframework.data.mybatis.repository.support;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mybatis.repository.MyBatisRepository;
import org.springframework.data.mybatis.repository.query.MybatisQueryLookupStrategy;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.util.Assert;

import java.io.Serializable;

public class MyBatisRepositoryFactory extends RepositoryFactorySupport {

    private final SqlSessionTemplate sessionTemplate;
    private EntityInformation entityInformation = null;

    public MyBatisRepositoryFactory(SqlSessionTemplate sessionTemplate) {
        super();
        Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null!");
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        if (null != entityInformation) return entityInformation;

        if (Persistable.class.isAssignableFrom(domainClass)) {
            entityInformation = new MybatisPersistableEntityInformation(domainClass);
        } else {
            entityInformation = new MybatisEntityInformation<T, ID>(domainClass);
        }
        return entityInformation;
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata repositoryMetadata) {
        return MyBatisRepository.class;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Object getTargetRepository(RepositoryMetadata repositoryMetadata) {
        return new SimpleMyBatisRepository(sessionTemplate, repositoryMetadata,getEntityInformation(repositoryMetadata.getDomainType()));
    }


    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(Key key, EvaluationContextProvider evaluationContextProvider) {
        return MybatisQueryLookupStrategy.create(sessionTemplate, key);
    }


}
