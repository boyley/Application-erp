package org.springframework.data.mybatis.repository.config;

import java.lang.annotation.*;

/**
 * Default SqlMapper marker annotation 
 * @author sbcoba
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapper {
}