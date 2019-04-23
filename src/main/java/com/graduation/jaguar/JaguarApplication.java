package com.graduation.jaguar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = { "com.graduation.jaguar.core.common", "com.graduation.jaguar.core.service","com.graduation.jaguar.core.dal","com.graduation.jaguar.web"})
@MapperScan(basePackages = {"com.graduation.jaguar.core.dal.dao"})
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class},scanBasePackages = "com.graduation.jaguar")
public class JaguarApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaguarApplication.class, args);
    }

}
