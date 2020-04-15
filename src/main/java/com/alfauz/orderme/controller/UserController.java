package com.alfauz.orderme.controller;

import com.alfauz.orderme.entity.UserEntity;
import com.alfauz.orderme.exception.BadRequestException;
import com.alfauz.orderme.exception.InternalServerErrorException;
import com.alfauz.orderme.exception.ResourceNotFoundException;
import com.alfauz.orderme.model.UserSummary;
import com.alfauz.orderme.payload.request.ChangePasswordRequest;
import com.alfauz.orderme.payload.response.ApiResponse;
import com.alfauz.orderme.security.CurrentUser;
import com.alfauz.orderme.security.service.UserPrincipal;
import com.alfauz.orderme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ApiResponse<UserSummary>> getCurrentUser(@CurrentUser UserPrincipal currentUser) {

        final Set<String> authorities = currentUser.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        UserSummary userSummary = UserSummary.builder()
                .id(currentUser.getId())
                .name(currentUser.getName())
                .username(currentUser.getUsername())
                .roles(authorities)
                .build();
        return ResponseEntity.ok(
                ApiResponse
                        .<UserSummary>builder()
                        .success(true)
                        .message("getCurrentUser response")
                        .entity(userSummary)
                        .build()
        );
    }

    @GetMapping("/user/checkUsernameAvailability")
    public ResponseEntity<ApiResponse<Boolean>> checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok(
                ApiResponse
                        .<Boolean>builder()
                        .success(true)
                        .message("checkUsernameAvailability response")
                        .entity(!userService.existsByUsername(username))
                        .build()
        );
    }

    @GetMapping("/user/checkEmailAvailability")
    public ResponseEntity<ApiResponse<Boolean>> checkEmailAvailability(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(
                ApiResponse
                        .<Boolean>builder()
                        .success(true)
                        .message("checkEmailAvailability response")
                        .entity(!userService.existsByEmail(email))
                        .build()
        );
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<ApiResponse<UserSummary>> getUserProfile(@PathVariable(value = "username") String username) {
        final UserEntity user = userService.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User", "username", username);
        }

        final Set<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        final String name = user.getFirstName() + (user.getMiddleName() != null ? " " + user.getMiddleName() : "") + " " + user.getLastName();

        final UserSummary userSummary = UserSummary.builder()
                .id(user.getId())
                .name(name)
                .username(user.getUsername())
                .roles(roles)
                .build();

        return ResponseEntity.ok(
                ApiResponse
                        .<UserSummary>builder()
                        .success(true)
                        .message("getUserProfile response")
                        .entity(userSummary)
                        .build()
        );
    }

    @PostMapping("/user/changePassword")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<Boolean>> changeUserPassword(@CurrentUser final UserPrincipal currentUserPrincipal, @RequestBody final ChangePasswordRequest request) {
        try {
            final UserEntity currentUser = userService.findById(currentUserPrincipal.getId());
            if (currentUser == null) {
                throw new ResourceNotFoundException("User", "id", currentUserPrincipal.getId());
            }

            if (StringUtils.isEmpty(request.getCurrentPassword())) {
                throw new BadRequestException("Current password is empty");
            } else if (StringUtils.isEmpty(request.getNewPassword())) {
                throw new BadRequestException("New password is empty");
            } else if (!passwordEncoder.matches(request.getCurrentPassword(), currentUser.getPassword())) {
                throw new BadRequestException("Current password is not same as actual password");
            } else if (request.getCurrentPassword().equals(request.getNewPassword())) {
                throw new BadRequestException("New password is same as current password");
            } else if (request.getNewPassword().length() < 6) {
                throw new BadRequestException("New password does not meet complexity requirements");
            } else {
                currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userService.save(currentUser);
                return ResponseEntity.ok(
                        ApiResponse
                                .<Boolean>builder()
                                .success(true)
                                .message("Password changed successfully")
                                .entity(true)
                                .build()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
