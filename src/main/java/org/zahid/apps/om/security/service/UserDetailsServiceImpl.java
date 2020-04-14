package org.zahid.apps.om.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zahid.apps.om.entity.User;
import org.zahid.apps.om.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepo userRepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String usernameOrEmail)
      throws UsernameNotFoundException {

    final User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() ->
            new UsernameNotFoundException("User Not Found with -> username or email : " + usernameOrEmail)
        );

    return UserPrincipal.build(user);
  }
}
