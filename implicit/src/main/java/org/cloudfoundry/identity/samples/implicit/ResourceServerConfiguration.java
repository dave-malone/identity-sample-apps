package org.cloudfoundry.identity.samples.implicit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("oauth_showcase_implicit_grant");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/access").access("#oauth2.hasScope('access')")
				.antMatchers("/admin").access("#oauth2.hasScope('admin')")
			.anyRequest().permitAll(); //[4];
	}

	@Bean
	public ResourceServerTokenServices tokenServices(){
//		DefaultTokenServices tokenServices = new DefaultTokenServices();
//		tokenServices.setTokenEnhancer(new JwtAccessTokenConverter());
//		tokenServices.setTokenStore(new InMemoryTokenStore());
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		
		tokenServices.setClientId("oauth_showcase_implicit_grant");
		tokenServices.setClientSecret("");
		tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/uaa/check_token");
		
		
		return tokenServices;
	}
	
	
}
