package com.gtaaustin.CRM.service;

import com.gtaaustin.CRM.entity.Intent;
import com.gtaaustin.CRM.entity.Topic;
import com.gtaaustin.CRM.repository.IntentRepository;
import com.gtaaustin.CRM.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("com.gtaustin.CRM.service.TrackingService")
public class TrackingService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    IntentRepository intentRepository;

    @Autowired
    HttpServletRequest request;

    public Boolean topicExists(String topicKey) {
        return topicRepository.findByCodeKey(topicKey).orElse(null) != null;
    }

    public Boolean trackIntentByTopicKey(String topicKey) {
        Boolean methodOutcome = false;

        if(topicExists(topicKey)) {
            Topic topic = topicRepository.findByCodeKey(topicKey).get();

            Intent intent = new Intent();
            intent.setRemoteHost(getRemoteAddress());
            intent.setTopic(topic);

            try {
                intentRepository.save(intent);
                methodOutcome = true;
            }
            catch(Exception e) {
                methodOutcome = false;
            }
        }

        return methodOutcome;
    }

    public String getRemoteAddress() {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}