package com.testinginspringboot.example.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author BYJ
 * @since 2024/09/26
 **/

class CompanyTest {
    @Test
    public void testAddress() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Company company = applicationContext.getBean("company", Company.class);
        assertEquals("high street", company.getAddress().getStreet());
        assertEquals(1000, company.getAddress().getNumber());
    }

}
