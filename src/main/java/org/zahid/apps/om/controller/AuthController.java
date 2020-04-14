package org.zahid.apps.om.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zahid.apps.om.enumeration.RoleName;
import org.zahid.apps.om.payload.request.SignUpRequest;
import org.zahid.apps.om.payload.response.ApiResponse;
import org.zahid.apps.om.payload.response.JwtAuthenticationResponse;
import org.zahid.apps.om.repo.RoleRepo;
import org.zahid.apps.om.repo.UserRepo;
import org.zahid.apps.om.entity.Role;
import org.zahid.apps.om.entity.User;
import org.zahid.apps.om.exception.BadRequestException;
import org.zahid.apps.om.exception.InternalServerErrorException;
import org.zahid.apps.om.mapper.UserMapper;
import org.zahid.apps.om.model.UserModel;
import org.zahid.apps.om.payload.request.LoginRequest;
import org.zahid.apps.om.security.jwt.JwtProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final String jwt = tokenProvider.generateJwtToken(authentication);
            return ResponseEntity.ok(
                    ApiResponse
                            .<JwtAuthenticationResponse>builder()
                            .success(true)
                            .message("Login successfull")
                            .entity(new JwtAuthenticationResponse(jwt))
                            .build()
            );
        } catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
//                    throw new AppException("BAD CREDENTIALS");
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Boolean>> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }

        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email Address already in use!");
        }

        // Creating userModel's account
        final UserModel userModel = UserModel.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();

        Set<String> strRoles = CollectionUtils.isNotEmpty(signUpRequest.getRole()) ? signUpRequest.getRole() : Set.of("user");
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepo.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException(
                                    "Fail! -> Cause: Role " + RoleName.ROLE_ADMIN.getValue() + " not found."));
                    roles.add(adminRole);
                    break;

                case "pm":
                    Role pmRole = roleRepo.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException(
                                    "Fail! -> Cause: Role " + RoleName.ROLE_PM.getValue() + " not found."));
                    roles.add(pmRole);
                    break;

        /*case "userModel":
          Role userRole = roleRepo.findByName(RoleName.ROLE_USER)
              .orElseThrow(() -> new RuntimeException(
                  "Fail! -> Cause: Role " + RoleName.ROLE_USER.getValue() + " not found."));
          roles.add(userRole);
          break;*/

                default:
                    Role userRole = roleRepo.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException(
                                    "Fail! -> Cause: Role " + RoleName.ROLE_USER.getValue() + " not found."));
                    roles.add(userRole);
            }
        });

        userModel.setRoles(roles);

        final User result = userRepo.saveAndFlush(userMapper.toEntity(userModel));

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location)
                .body(
                        ApiResponse
                                .<Boolean>builder()
                                .success(true)
                                .message("Thank you! You're successfully registered. Please Login to continue!")
                                .entity(true)
                                .build()
                );
    }

    @GetMapping("/checkEmailExists")
    public ResponseEntity<ApiResponse<Boolean>> checkEmailExists(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(
                ApiResponse
                        .<Boolean>builder()
                        .success(userRepo.existsByEmail(email))
                        .message("Email already exists")
                        .entity(userRepo.existsByEmail(email))
                        .build()
        );
    }

    @GetMapping("/recoverPassword")
    public ResponseEntity<ApiResponse<Boolean>> recoverPassword(@RequestParam(value = "email") String email) {
        final ResponseEntity<ApiResponse<Boolean>> responseEntity = checkEmailExists(email);

        if (responseEntity.getBody().equals(true)) {
            try {
//                final boolean mailSent = gmailService.sendMessage("Welcome to OM Application", "To reset you account password, please click on below link:\nhttp://localhost:3000", email);
//                if (mailSent) {
                    return ResponseEntity.ok(
                            ApiResponse
                                    .<Boolean>builder()
                                    .success(true)
                                    .message("Recovery email sent")
                                    .entity(null)
                                    .build()
                    );
//                } else {
//                    throw new BadRequestException("Unknown error occurred");
//                }
//            } catch (MessagingException | IOException e) {
//                e.printStackTrace();
//                throw new InternalServerErrorException(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                throw new InternalServerErrorException(e.getMessage());
            }
        } else {
            throw new BadRequestException("Email not registered");
        }
    }
}
