package org.cloudfoundry.identity.samples.implicit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


//	@Value("${spring.oauth2.client.clientId:client_id_placeholder}")
//	private String clientId;
//	@Value("${security.oauth2.client.clientSecret:client_secret_placeholder")
//	private String clientSecret;
//	@Value("${tokenVerificationKey:http://localhost:8080/uaa/check_token}")
//	private String checkTokenUrl;



	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("6d490fcf-20ee-4d08-a435-396584d3848a");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.addFilterAfter(new BearerTokenOncePerRequestFilter(), AbstractPreAuthenticatedProcessingFilter.class)
				.authorizeRequests().anyRequest().permitAll();
	}


//	@Bean
//	public ResourceServerTokenServices tokenServices(){
//		RemoteTokenServices tokenServices = new RemoteTokenServices();
//
//		tokenServices.setClientId(clientId);
//		tokenServices.setClientSecret(clientSecret);
//		tokenServices.setCheckTokenEndpointUrl("https://sso-internal.login.system.pcf.local/token_key");
//
//
//		return tokenServices;
//	}
	
	
}
