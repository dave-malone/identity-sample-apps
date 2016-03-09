package org.cloudfoundry.identity.samples.implicit.config;

import org.cloudfoundry.identity.samples.implicit.filter.BearerTokenOncePerRequestFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//TODO - get this value from env vars/system props
		resources.resourceId("6d490fcf-20ee-4d08-a435-396584d3848a");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.addFilterAfter(new BearerTokenOncePerRequestFilter(), AbstractPreAuthenticatedProcessingFilter.class)
				.authorizeRequests().anyRequest().permitAll();
	}

	
	
}
