package org.springframework.data.mybatis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * MyBatis specific extension of {@link org.springframework.data.repository.Repository}.
 *
 * @author Jean-Christophe Lagache
 */
@NoRepositoryBean
public interface MyBatisRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    <X extends T> Page<T> findAll(Pageable pageable, X condition);

    <X extends T> Iterable<T> findAll(X condition);

    <X extends T> Iterable<T> findAll(Sort sort, X condition);
}
