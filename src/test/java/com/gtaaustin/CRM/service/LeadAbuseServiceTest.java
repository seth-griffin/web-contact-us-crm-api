package com.gtaaustin.CRM.service;

import com.gtaaustin.CRM.entity.Lead;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeadAbuseServiceTest {

    @Autowired
    public LeadAbuseService leadAbuseService;

    @Test
    public void testContainsAbusiveLanguage() {
        /**
         * A variety of things will potentially be tested within lead structure so we'll test multiple cases
         */

        ArrayList<String> testList = leadAbuseService.loadNaughtyWords();

        // Mixture
        Lead testLead = new Lead();
        testLead.setName("Mike Smith");
        testLead.setEmail("msmith@example.com");
        testLead.setPhone("ur a stupid asshole");
        testLead.setMessage("Fuckufuckinahole");
        assertTrue(leadAbuseService.containsAbusiveLanguage(testLead));

        // Name only
        Lead testLead2 = new Lead();
        testLead2.setName("Fuck Face");
        testLead2.setEmail("msmith@example.com");
        testLead2.setPhone("555-555-5555");
        testLead2.setMessage("Msmith contact u");
        assertTrue(leadAbuseService.containsAbusiveLanguage(testLead2));

        // Email only
        Lead testLead3 = new Lead();
        testLead3.setName("Mike Smith");
        testLead3.setEmail("fuckubro@example.com");
        testLead3.setPhone("555-555-5555");
        testLead3.setMessage("Msmith contact u 'gain");
        assertTrue(leadAbuseService.containsAbusiveLanguage(testLead3));

        // Phone only
        Lead testLead4 = new Lead();
        testLead4.setName("Mike Smith");
        testLead4.setEmail("msmith@example.com");
        testLead4.setPhone("Stupid dick shit othernastystuff");
        testLead4.setMessage("Keeping it vanilla here for the kids");
        assertTrue(leadAbuseService.containsAbusiveLanguage(testLead4));

        // Phone only
        Lead testLead5 = new Lead();
        testLead5.setName("Mike Smith");
        testLead5.setEmail("msmith@example.com");
        testLead5.setPhone("Stupid dick shit othernastystuff");
        testLead5.setMessage("Keeping it vanilla here for the kids");
        assertTrue(leadAbuseService.containsAbusiveLanguage(testLead5));

        // Perfect lead
        Lead testLead6 = new Lead();
        testLead6.setName("Mike Smith");
        testLead6.setEmail("msmith@example.com");
        testLead6.setPhone("555-555-5555");
        testLead6.setMessage("Please reach out to us we need to plan an IT transformation");
        assertFalse(leadAbuseService.containsAbusiveLanguage(testLead6));
    }
}
