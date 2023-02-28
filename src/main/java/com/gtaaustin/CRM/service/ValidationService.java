package com.gtaaustin.CRM.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.springframework.stereotype.Service;

@Service("com.gtaaustin.CRM.service.ValidationService")
public class ValidationService {

    public static final String PHONE_PATTERN = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";

    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    public Boolean isPhone(String testString) {
        return quickRegexMatch(testString, PHONE_PATTERN);
    }

    public Boolean isEmail(String testString) {
        return quickRegexMatch(testString, EMAIL_PATTERN);
    }

    public Boolean quickRegexMatch(String testString, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(testString);

        return matcher.matches();
    }
}