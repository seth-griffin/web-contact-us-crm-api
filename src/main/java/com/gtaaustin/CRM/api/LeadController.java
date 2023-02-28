package com.gtaaustin.CRM.api;

import com.gtaaustin.CRM.api.RequestEntities.LeadRequestBody;
import com.gtaaustin.CRM.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/crm/leads")
public class LeadController {

    @Autowired
    LeadService leadService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> trackIntent(@RequestBody LeadRequestBody leadRequestBody) {
        HttpStatus status = HttpStatus.OK;

        if(!leadService.addLead(
                leadRequestBody.getEmail(),
                leadRequestBody.getMessage(),
                leadRequestBody.getName(),
                leadRequestBody.getPhone(),
                leadRequestBody.getSubject()
        )) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>("", status);
    }
}