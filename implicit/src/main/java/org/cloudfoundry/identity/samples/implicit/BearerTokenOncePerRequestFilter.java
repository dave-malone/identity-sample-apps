package org.cloudfoundry.identity.samples.implicit;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dan on 3/8/16.
 */
public class BearerTokenOncePerRequestFilter extends OncePerRequestFilter{

    private final TokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(tokenExtractor.extract(request) == null){
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

}
