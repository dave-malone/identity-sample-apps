package org.cloudfoundry.identity.samples.implicit.config;

import org.apache.log4j.Logger;
import org.cloudfoundry.identity.samples.implicit.filter.BearerTokenOncePerRequestFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


	private static final Logger logger = Logger.getLogger(ResourceServerConfig.class);

	@Value("${security.oauth2.client.clientId:default_resource_id}")
	private String resourceId;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		logger.debug("INJECTED_RESOURCE_ID: "+ resourceId);
		resources.resourceId(resourceId);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.addFilterAfter(new BearerTokenOncePerRequestFilter(), AbstractPreAuthenticatedProcessingFilter.class)
				.authorizeRequests().anyRequest().permitAll();
	}

	
	
}
