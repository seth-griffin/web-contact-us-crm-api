package com.gtaaustin.CRM.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceTest {

    @Autowired
    public ValidationService validationService;

    @Test
    public void testIsEmail() {
        assertTrue(validationService.isEmail("example@example.com"));
        assertTrue(validationService.isEmail("seth.griffin@gtaaustin.com"));
        assertFalse(validationService.isEmail("Holy cow I faked this field"));
    }

    @Test
    public void testIsPhone() {
        assertTrue(validationService.isPhone("555-555-5555"));
        assertTrue(validationService.isPhone("555.555.5555"));
        assertFalse(validationService.isPhone("Wow this message is going to blow some sales dudes mind maaan"));
        assertTrue(validationService.isPhone("555 555 5555"));
    }
}
