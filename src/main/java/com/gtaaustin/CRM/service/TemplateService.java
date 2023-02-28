package com.gtaaustin.CRM.service;

import com.gtaaustin.CRM.entity.Template;
import com.gtaaustin.CRM.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("com.gtaustin.CRM.service.TemplateService")
public class TemplateService {

    @Autowired
    TemplateRepository templateRepository;

    public String processTemplate(HashMap<String, String> parameters, String templateText) {

        for(HashMap.Entry<String, String> entry : parameters.entrySet()) {
            String token = String.format("{{%s}}", entry.getKey());
            templateText = templateText.replace(token, entry.getValue());
        }

        return templateText;
    }

    public Template getTemplateByCodeKey(String codeKey) {
        return templateRepository.findByCodeKey(codeKey);
    }
}
