package com.alfauz.orderme.entity;

import com.alfauz.orderme.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS", schema = "OM", uniqueConstraints = {
        @UniqueConstraint(name = "username_uk", columnNames = {"username"}),
        @UniqueConstraint(name = "email_uk", columnNames = {"email"})
})
public class UserEntity extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "First name must not be blank")
    @Size(min = 1, max = 20, message = "First name should has min. 1 and max. 20 characters")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 20, message = "Middle name should has max. 20 characters")
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 1, max = 20, message = "Last name should has min. 1 and max. 20 characters")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NaturalId
    @NotBlank(message = "Email must not be blank")
    @Size(min = 6, max = 50, message = "Email should has min. 6 and max. 50 characters")
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username should has min. 3 and max. 50 characters")
    @Column(name = "USERNAME")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password should has min. 6 and max. 100 characters")
    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "USER_TYPE")
    private UserType userType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade(value = CascadeType.ALL)
    private List<UserAddressEntity> userAddresses;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}
