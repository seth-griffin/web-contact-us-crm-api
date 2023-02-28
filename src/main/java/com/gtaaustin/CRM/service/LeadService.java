package com.gtaaustin.CRM.service;

import com.gtaaustin.CRM.entity.BlacklistedHostLead;
import com.gtaaustin.CRM.entity.Lead;
import com.gtaaustin.CRM.repository.LeadRepository;
import com.gtaaustin.CRM.repository.BlacklistedHostLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("com.gtaaustin.CRM.service.LeadService")
public class LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    LeadAbuseService leadAbuseService;

    @Autowired
    BlacklistService blacklistService;

    @Autowired
    BlacklistedHostLeadRepository blacklistedHostLeadRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    TrackingService trackingService;

    public Boolean addLead(String email, String message, String name, String phone, String subject) {
        Boolean methodOutcome = false;
        String remoteHost = trackingService.getRemoteAddress();

        Lead lead = new Lead();
        lead.setEmail(email);
        lead.setMessage(message);
        lead.setRemoteHost(remoteHost);
        lead.setName(name);
        lead.setPhone(phone);
        lead.setSubject(subject);

        if(leadAbuseService.runAllAbuseChecks(lead) == false) {
            blacklistService.addToBlacklist(remoteHost);
        }

        try {
            leadRepository.save(lead);
            methodOutcome = true;
        }
        catch(Exception e) {
            methodOutcome = false;
        }

        if(!blacklistService.isHostBlacklisted(remoteHost)) {
            HashMap<String, String> templateParameters = new HashMap<String, String>();
            templateParameters.put("name", name);
            templateParameters.put("email", email);
            templateParameters.put("phone", phone);
            templateParameters.put("subject", subject);
            templateParameters.put("message", message);

            messageService.sendEmailMessage("info@gtaaustin.com", "New Lead", "contact_us", templateParameters);
        }
        else {
            migrateLeadToBlackList(lead);
        }


        return methodOutcome;
    }

    public Boolean migrateLeadToBlackList(Lead lead) {
        Boolean methodOutcome = false;

        BlacklistedHostLead blacklistedHostLead = new BlacklistedHostLead();
        blacklistedHostLead.setEmail(lead.getEmail());
        blacklistedHostLead.setMessage(lead.getMessage());
        blacklistedHostLead.setRemoteHost(lead.getRemoteHost());
        blacklistedHostLead.setName(lead.getName());
        blacklistedHostLead.setPhone(lead.getPhone());
        blacklistedHostLead.setSubject(lead.getSubject());

        Boolean abuseCheckOutcome = leadAbuseService.runAllAbuseChecks(lead);
        blacklistService.addToBlacklist(lead.getRemoteHost());

        try {
            blacklistedHostLeadRepository.save(blacklistedHostLead);
            leadRepository.delete(lead);
            methodOutcome = true;
        }
        catch(Exception e) {
            methodOutcome = false;
        }


        return methodOutcome;
    }
}