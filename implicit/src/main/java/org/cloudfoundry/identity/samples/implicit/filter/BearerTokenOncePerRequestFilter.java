package org.cloudfoundry.identity.samples.implicit.filter;

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
 * This filter looks to see whether the BearerTokenExtractor is able to extract a filter or not. If it isn't able to do so,
 * then it clears the Spring SecurityContext. Either way, this filter does not halt processing of the FilterChain
 * 
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
