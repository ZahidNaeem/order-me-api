package com.alfauz.orderme.security.service;

import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String usernameOrEmail)
            throws UsernameNotFoundException {

        final UserEntity user = userService.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + usernameOrEmail);
        }

        return UserPrincipal.build(user);
    }
}
