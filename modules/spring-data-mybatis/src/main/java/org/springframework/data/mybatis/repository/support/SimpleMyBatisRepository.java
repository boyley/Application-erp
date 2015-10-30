package org.springframework.data.mybatis.repository.support;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mybatis.repository.MyBatisRepository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Specifies Default binding for findOne, findAll, exists, count methods
 * between org.springframework.data.mybatis.repository.MyBatisRepository and mybatis mapper
 */
public class SimpleMyBatisRepository<T, ID extends Serializable> extends SqlSessionRepositorySupport implements MyBatisRepository<T, ID> {

    private static final String FIND = "find";

    private final RepositoryMetadata repositoryMetadata;
    private final EntityInformation<T, ID> entityInformation;

    public SimpleMyBatisRepository(SqlSessionTemplate sessionTemplate, RepositoryMetadata repositoryMetadata, EntityInformation<T, ID> entityInformation) {
        super(sessionTemplate);
        Assert.notNull(repositoryMetadata);
        Assert.notNull(entityInformation);
        this.repositoryMetadata = repositoryMetadata;
        this.entityInformation = entityInformation;
    }

    @Override
    public <S extends T> S save(S entity) {
        boolean isNew = entityInformation.isNew(entity);
        if (isNew) {
            insert("insert", entity);
        } else {
            update("update", entity);
        }
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        if (null == entities) return entities;
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    @Override
    public T findOne(ID id) {
        Map<String, ID> params = new HashMap<>();
        params.put("pk", id);
        return selectOne("find", params);
    }

    @Override
    public List<T> findAll() {
        Map<String, ID> params = new HashMap<>();
        return selectList("find", params);
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        return selectList("selectByIds", params);
    }


    @Override
    public boolean exists(ID id) {
        return findOne(id) != null;
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void delete(ID id) {
        super.delete("deleteById", id);
    }

    @Override
    public void delete(T entity) {
        ID id = entityInformation.getId(entity);
        delete(id);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        if (null == entities) return;
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        super.delete("deleteAll");
    }


    @Override
    protected String getNamespace() {
        return repositoryMetadata.getRepositoryInterface().getCanonicalName();
    }

    @Override
    public <X extends T> Page<T> findAll(Pageable pageable, X condition) {
        return findByPager(pageable, FIND, condition);
    }

    @Override
    public <X extends T> Iterable<T> findAll(X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        return selectList(FIND, params);
    }

    @Override
    public <X extends T> Iterable<T> findAll(Sort sort, X condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        params.put("sorts", sort);
        return selectList(FIND, params);
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sorts", sort);
        return selectList(FIND, params);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return findAll(pageable, null);
    }
}
