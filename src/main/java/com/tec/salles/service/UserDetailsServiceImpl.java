package com.tec.salles.service;

import com.tec.salles.dto.Login;
import com.tec.salles.entity.UserAccess;
import com.tec.salles.repository.UserAccessRepository;
import com.tec.salles.security.configuration.UserDetailImpl;
import com.tec.salles.security.property.JwtConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAccessRepository userAccessRepository;
    private final JwtConfiguration jwtConfiguration;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDetailsServiceImpl(final UserAccessRepository userAccessRepository, JwtConfiguration jwtConfiguration, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAccessRepository = userAccessRepository;
        this.jwtConfiguration = jwtConfiguration;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetailImpl loadUserByUsername(final String login) throws UsernameNotFoundException {
        return null;
    }

    public UserDetailImpl loadUserByUsername(final Login login) {
        Optional<UserAccess> optionalUser = this.userAccessRepository.findByUsername(login.getUsername());

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(login.getUsername());
        }

        if(!this.bCryptPasswordEncoder.matches(login.getPassword(), optionalUser.get().getPassword())) {
            throw new AccessDeniedException(login.getUsername());
        }

        return new UserDetailImpl()
                .setToken(this.jwtConfiguration.generateToken(login.getUsername()))
                .setId(optionalUser.get().getId())
                .setEmail(optionalUser.get().getEmail())
                .setPassword(optionalUser.get().getPassword());
    }
}
