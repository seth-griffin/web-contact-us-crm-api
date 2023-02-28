package com.gtaaustin.CRM.api;

import com.gtaaustin.CRM.api.RequestEntities.IntentRequestBody;
import com.gtaaustin.CRM.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/crm/tracking")
public class TrackingController {

    @Autowired
    TrackingService trackingService;

    @RequestMapping(value = "/intents", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> trackIntent(@RequestBody IntentRequestBody intentRequestBody) {
        HttpStatus status = HttpStatus.OK;
        if(!trackingService.trackIntentByTopicKey(intentRequestBody.getTopic())) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<String>("", status);
    }

}