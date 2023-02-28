package com.gtaaustin.CRM.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateServiceTest {

    @Autowired
    public TemplateService templateService;

    public String templateText;

    public HashMap<String, String> templateParameters;

    public String processedTemplateText;

    @Before
    public void setUp() {
        setUpTemplateText();
        setUpProcessedTemplateText();
        setUpTemplateParameters();
    }

    protected void setUpTemplateText() {
        templateText = "<html>\n" +
        "   <head>\n" +
        "       <title>{{message.subject}}</title>\n" +
        "   </head>\n" +
        "   <body>\n" +
        "       <table>\n" +
        "           <tr>\n" +
        "               <td>Name:</td>\n" +
        "               <td>{{name}}</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Email:</td>\n" +
        "               <td>{{email}}</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Phone:</td>\n" +
        "               <td>{{phone}}</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Subject:</td>\n" +
        "               <td>{{subject}}</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Message:</td>\n" +
        "               <td>{{message}}</td>\n" +
        "           </tr>\n" +
        "           </tr>\n" +
        "       </table>\n" +
        "   </body>\n" +
        "</html>\n";
    }

    protected void setUpProcessedTemplateText() {
        processedTemplateText = "<html>\n" +
        "   <head>\n" +
        "       <title>Test Subject</title>\n" +
        "   </head>\n" +
        "   <body>\n" +
        "       <table>\n" +
        "           <tr>\n" +
        "               <td>Name:</td>\n" +
        "               <td>Mitch McCool</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Email:</td>\n" +
        "               <td>mmccool@example.com</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Phone:</td>\n" +
        "               <td>555-123-1212</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Subject:</td>\n" +
        "               <td>Need professional advice</td>\n" +
        "           </tr>\n" +
        "           <tr>\n" +
        "               <td>Message:</td>\n" +
        "               <td>Hey there. Found your site while on the search engine...</td>\n" +
        "           </tr>\n" +
        "           </tr>\n" +
        "       </table>\n" +
        "   </body>\n" +
        "</html>\n";
    }

    protected void setUpTemplateParameters() {
        templateParameters = new HashMap<String, String>();
        templateParameters.put("message.subject", "Test Subject");
        templateParameters.put("name", "Mitch McCool");
        templateParameters.put("email", "mmccool@example.com");
        templateParameters.put("phone", "555-123-1212");
        templateParameters.put("subject", "Need professional advice");
        templateParameters.put("message", "Hey there. Found your site while on the search engine...");
    }

    @Test
    public void testProcessTemplate() {
        String processTemplateReturnVal = templateService.processTemplate(templateParameters, templateText);
        assertEquals(processTemplateReturnVal, processedTemplateText);
    }
}
