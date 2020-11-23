package com.tec.salles.security.filters;

import com.tec.salles.security.configuration.UserDetailImpl;
import com.tec.salles.security.property.JwtConfiguration;
import com.tec.salles.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private final JwtConfiguration jwtConfiguration;
    private final UserDetailsServiceImpl userDetailService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, final JwtConfiguration jwtConfiguration,
                                   final UserDetailsServiceImpl userDetailService) {
        super(authenticationManager);
        this.jwtConfiguration = jwtConfiguration;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer")) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header.substring(7));

            if (authenticationToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(final String token) {
        if (this.jwtConfiguration.isValidToken(token)) {
            String username = this.jwtConfiguration.getUserName(token);
            UserDetailImpl userDetail = this.userDetailService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        }
        return null;
    }
}
