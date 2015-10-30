package org.springframework.data.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mybatis.repository.config.EnableMyBatisRepositories;

@SpringBootApplication
@EnableMyBatisRepositories
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
