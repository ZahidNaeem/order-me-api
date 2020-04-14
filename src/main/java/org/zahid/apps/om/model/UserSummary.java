package org.zahid.apps.om.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private Set<String> roles = new HashSet<>();
}
