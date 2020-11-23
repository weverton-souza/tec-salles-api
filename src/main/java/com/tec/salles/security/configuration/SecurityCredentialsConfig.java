package com.tec.salles.security.configuration;

import com.tec.salles.security.filters.JwtAuthenticationFilter;
import com.tec.salles.security.property.JwtConfiguration;
import com.tec.salles.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtConfiguration jwtConfiguration;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String[] PUBLIC_MATCHERS = {
            "/auth/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v1/**"
    };

    private static final String[] PUBLIC_MATCHERS_OPTIONS = {
            "/v1/**"
    };

    @Autowired
    public SecurityCredentialsConfig(final UserDetailsServiceImpl userDetailsServiceImpl,
                                     final JwtConfiguration jwtConfiguration, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtConfiguration = jwtConfiguration;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/login",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/orders/**",
                "/products/**",
                "/customers/**",
                "/categories/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .disable()
                .csrf()
                .disable();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, PUBLIC_MATCHERS_OPTIONS).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();
        http
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),
                        this.jwtConfiguration, userDetailsServiceImpl));
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(this.bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
