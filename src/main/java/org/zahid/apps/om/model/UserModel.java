package org.zahid.apps.om.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zahid.apps.om.entity.Auditable;
import org.zahid.apps.om.entity.Role;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends Auditable<Long> {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
