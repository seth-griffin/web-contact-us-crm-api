package com.gtaaustin.CRM.api.RequestEntities;

import lombok.Data;

public @Data
class LeadRequestBody {
    String name;
    String email;
    String phone;
    String subject;
    String message;
}