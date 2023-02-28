package com.gtaaustin.CRM.service;

import com.gtaaustin.CRM.entity.BlacklistedHostLead;
import com.gtaaustin.CRM.entity.Lead;
import com.gtaaustin.CRM.repository.BlacklistedHostLeadRepository;
import com.gtaaustin.CRM.repository.BlacklistedHostRepository;
import com.gtaaustin.CRM.repository.LeadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

@Service("com.gtaustin.CRM.service.LeadAbuseService")
public class LeadAbuseService {

    @Autowired
    ValidationService validationService;

    @Autowired
    BlacklistedHostRepository blacklistedHostRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    BlacklistedHostLeadRepository blackListedHostLeadRepository;

    Logger logger = LoggerFactory.getLogger(LeadAbuseService.class);

    /**
     * Ensures that phone and email fields contain well formatted input. True is desired outcome
     * @param lead
     * @return
     */
    public Boolean metaDataValidates(Lead lead) {
        return validationService.isEmail(lead.getEmail()) && validationService.isPhone(lead.getPhone());
    }

    /**
     * Ensures that abusive language is not in any user input within the lead. False is desired outcome
     * @param lead
     * @return
     */
    public Boolean containsAbusiveLanguage(Lead lead) {
        Boolean methodOutcome = false;

        ArrayList<String> naughtyWords = loadNaughtyWords();

        for(String tsk : naughtyWords) {
            if(lead.getMessage().toLowerCase().contains(tsk) ||
                    lead.getName().toLowerCase().contains(tsk) ||
                    lead.getPhone().toLowerCase().contains(tsk) ||
                    lead.getEmail().toLowerCase().contains(tsk)) {
                methodOutcome = true;
                break;
            }
        }

        return methodOutcome;
    }

    public ArrayList<String> loadNaughtyWords() {
        ArrayList<String> list = new ArrayList<>();

        try {
            Resource resource = new ClassPathResource("static/naughty_words.txt");
            InputStream is = resource.getInputStream();
            Scanner scanner = new Scanner(is);

            while(scanner.hasNextLine())
                list.add(scanner.nextLine());

        }
        catch(Exception e) {
            logger.debug(e.getMessage());
        }

        return list;
    }

    /**
     * Ensure host has not been seen yet today. True is desired outcome
     * @param lead
     * @return
     */
    public Boolean hostIsWithinRateLimit(Lead lead) {
        Boolean methodOutcome = false;

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date startDate = cal.getTime();
        Date endDate = new Date();

        ArrayList<BlacklistedHostLead> blackListedHostLeads = blackListedHostLeadRepository.getAllBetweenDates(lead.getRemoteHost(), startDate, endDate);
        ArrayList<Lead> leads = leadRepository.getAllBetweenDates(lead.getRemoteHost(), startDate, endDate);

        logger.info("Leads size is " + leads.size());
        logger.info("Blacklisted host leads size is  " + leads.size());
        if((leads.size() + blackListedHostLeads.size()) == 0) {
            methodOutcome = true;
        }

        return methodOutcome;
    }

    public Boolean runAllAbuseChecks(Lead lead) {
        return hostIsWithinRateLimit(lead) && !containsAbusiveLanguage(lead) && metaDataValidates(lead);
    }
}