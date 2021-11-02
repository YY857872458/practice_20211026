package com.example.practice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(classes = Application.class, webEnvironment = NONE)
public class PersistenceTests {
    @Autowired
    private DataSource dataSource;

    public DataSource getDataSource(){
        return dataSource;
    }
}
