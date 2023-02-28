package com.gtaaustin.CRM.service;

import com.gtaaustin.CRM.entity.BlacklistedHost;
import com.gtaaustin.CRM.repository.BlacklistedHostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("com.gtaustin.CRM.service.BlacklistService")
public class BlacklistService  {

    @Autowired
    BlacklistedHostRepository blacklistedHostRepository;

    public Boolean addToBlacklist(String remoteHost) {
        Boolean methodOutcome = false;

        if(!isHostBlacklisted(remoteHost)) {
            BlacklistedHost blacklistedHost = new BlacklistedHost();
            blacklistedHost.setRemoteHost(remoteHost);

            try {
                blacklistedHostRepository.save(blacklistedHost);
                methodOutcome = true;
            }
            catch(Exception e) {
                methodOutcome = false;
            }
        }
        else {
            methodOutcome = true;
        }

        return methodOutcome;
    }

    public Boolean isHostBlacklisted(String remoteHost) {
        return blacklistedHostRepository.findByRemoteHost(remoteHost).orElse(null) != null;
    }
}