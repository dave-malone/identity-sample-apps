package org.cloudfoundry.identity.samples.implicit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by dan on 3/9/16.
 */
@RestController
@RequestMapping("/api")
public class ExampleRestController {

    @RequestMapping("/public")
    public Map<String, String> unsecured(HttpServletRequest request, Model model) {
        Map<String, String> responseObject = new HashMap<String, String>();

        responseObject.put("message", "This is an unsecured endpoint.");

        return responseObject;
    }

    @RequestMapping("/secured/access")
    @PreAuthorize("#oauth2.hasScope('testaccess')")
    //full list of methods: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/expression/OAuth2SecurityExpressionMethods.html
    public Map<String, String> access(HttpServletRequest request, Model model) {
        Map<String, String> responseObject = new HashMap<String, String>();

        responseObject.put("message", "This is a secured endpoint requiring the 'testaccess' scope");

        return responseObject;
    }

    @RequestMapping("/secured/admin")
    @PreAuthorize("#oauth2.hasScope('testadmin')")
    public Map<String, String> admin(HttpServletRequest request, Model model) {
        Map<String, String> responseObject = new HashMap<String, String>();

        responseObject.put("message", "This is a secured endpoint requiring the 'testadmin' scope");

        return responseObject;
    }

}
