package com.lyloou.demo;

import com.lyloou.demo.dao.RoleDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {


    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;


    @Bean
    public MapperFactoryBean<RoleDao> initRoleDao() {

        MapperFactoryBean<RoleDao> roleMapper = new MapperFactoryBean<>();

        roleMapper.setMapperInterface(RoleDao.class);

        roleMapper.setSqlSessionTemplate(sqlSessionTemplate);
        return roleMapper;
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
