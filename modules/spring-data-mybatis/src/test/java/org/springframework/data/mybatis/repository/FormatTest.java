package org.springframework.data.mybatis.repository;

import org.junit.Test;

/**
 * Created by lenovo on 2015/10/11.
 */
public class FormatTest {

    @Test
    public void testFormat() {
        String queryName = String.format("%s.%s", "org.springframework.data.mybatis.repository", "testFormat");
        System.out.println(queryName);
    }

}
