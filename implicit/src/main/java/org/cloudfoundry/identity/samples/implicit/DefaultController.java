package org.cloudfoundry.identity.samples.implicit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @Value("${ssoServiceUrl:http://localhost:8080/uaa}")
    private String ssoServiceUrl;

    @Value("${security.oauth2.client.clientId:oauth_showcase_implicit_grant}")
    private String clientId;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        model.addAttribute("ssoServiceUrl", ssoServiceUrl);
        model.addAttribute("thisUrl", UrlUtils.buildFullRequestUrl(request));
        model.addAttribute("clientId", clientId);
        if (clientId.equals("client_id_placeholder")) {
            return "configure_warning";
        }
        return "index";
    }
  
    

    @RequestMapping("/access")
    @PreAuthorize("#oauth2.hasScope('testaccess')")
    //full list of methods: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/expression/OAuth2SecurityExpressionMethods.html
    public String access(HttpServletRequest request, Model model) {
    	return "access";
    }
    
    @RequestMapping("/admin")
    @PreAuthorize("#oauth2.hasScope('testadmin')")
    public String admin(HttpServletRequest request, Model model) {
    	return "admin";
    }
    
	
}
