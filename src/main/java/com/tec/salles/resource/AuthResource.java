package com.tec.salles.resource;

import com.tec.salles.dto.Login;
import com.tec.salles.security.configuration.UserDetailImpl;
import com.tec.salles.service.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@Api(value = "Auth", tags = ":: AUTH ::", description = "Auth resources")
public class AuthResource {
    private final UserDetailsServiceImpl userDetailsService;

    public AuthResource(final UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/login")
    public UserDetailImpl loadUserAccess(@RequestBody final Login login) {
        return this.userDetailsService.loadUserByUsername(login);
    }
}
