package com.example.multimodule.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest("service.message=Hello World")
@SpringBootTest
public class MyServiceTest {
    @Autowired
    private MyService myService;

    @Test
    public void contextLoads() {
        assertThat(myService.message()).isNotNull();
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
