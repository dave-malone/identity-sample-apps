package org.cloudfoundry.identity.samples.implicit.controller;

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
  

    
	
}
