package com.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.framework.modules.*.dao"})
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("framework-admin访问路径：http://localhost:8080/framework-admin");
        System.out.println("swagger文档路径：http://localhost:8080/framework-admin/swagger/index.html");
        System.out.println("swagger注解路径：http://localhost:8080/framework-admin/swagger-ui.html");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }
}
