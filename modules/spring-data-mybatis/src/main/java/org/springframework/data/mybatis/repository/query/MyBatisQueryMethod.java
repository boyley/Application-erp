package org.springframework.data.mybatis.repository.query;

import java.lang.reflect.Method;

import org.springframework.data.mybatis.repository.annotations.MybatisQuery;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class MyBatisQueryMethod extends QueryMethod {
	
	private final Class<?> mapperInterface;
	private final Method method;

	public MyBatisQueryMethod(Method method, RepositoryMetadata metadata) {
		super(method, metadata);
		Assert.notNull(method, "Method must not be null!");
		Assert.notNull(metadata, "RepositoryMetadata must not be null!");
		this.method = method;
		mapperInterface = metadata.getRepositoryInterface();
	}
	
	public String getMappedStatementId() {
		String queryName = String.format("%s.%s",mapperInterface.getName(), method.getName());
		MybatisQuery mybatisQuery = method.getAnnotation(MybatisQuery.class);
		if (null != mybatisQuery && !StringUtils.isEmpty(mybatisQuery.value())) {
			queryName = mybatisQuery.value();
		}
		return queryName;
	}

	public Class<?> getRepositoryInterface() {
		return mapperInterface;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public String getNamedQueryName() {
		return super.getNamedQueryName();
	}
}
