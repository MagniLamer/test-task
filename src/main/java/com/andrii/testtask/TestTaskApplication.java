package com.andrii.testtask;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TestTaskApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(TestTaskApplication.class, args);
//        Console.main();
    }

}
