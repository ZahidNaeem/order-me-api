package com.alfauz.orderme.controller;

import com.alfauz.orderme.entity.RoleEntity;
import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.enumeration.RoleName;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.exception.InternalServerErrorException;
import com.alfauz.orderme.mapper.UserMapper;
import com.alfauz.orderme.model.UserModel;
import com.alfauz.orderme.payload.request.LoginRequest;
import com.alfauz.orderme.payload.request.SignupRequest;
import com.alfauz.orderme.payload.response.ApiResponse;
import com.alfauz.orderme.payload.response.JwtAuthenticationResponse;
import com.alfauz.orderme.security.jwt.JwtProvider;
import com.alfauz.orderme.service.RoleService;
import com.alfauz.orderme.service.UserService;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final RoleService roleService;

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
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
//                    throw new AppException("BAD CREDENTIALS");
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Boolean>> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email Address already in use!");
        }

        // Creating userModel's account
        final UserModel userModel = UserModel.builder()
                .firstName(signUpRequest.getFirstName())
                .middleName(signUpRequest.getMiddleName())
                .lastName(signUpRequest.getLastName())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .userType(signUpRequest.getUserType())
                .userAddresses(signUpRequest.getUserAddresses())
                .build();

        final UserEntity userEntity = userMapper.toEntity(userModel);

        Set<String> roleValues = CollectionUtils.isNotEmpty(signUpRequest.getRoles()) ? signUpRequest.getRoles() : Set.of("user");
        Set<RoleEntity> roles = new HashSet<>();

        roleValues.forEach(value -> {
            final RoleEntity role = roleService.findByName(RoleName.fromValue(value));
            if (role == null) {
                throw new RuntimeException(
                        "Fail! -> Cause: Role " + RoleName.ROLE_USER.getValue() + " not found.");
            }
            roles.add(role);
        });


        userEntity.setRoles(roles);

        final UserEntity result = userService.save(userEntity);

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
                        .success(userService.existsByEmail(email))
                        .message("Email already exists")
                        .entity(userService.existsByEmail(email))
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
