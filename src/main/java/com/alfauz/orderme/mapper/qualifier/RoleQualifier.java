package com.alfauz.orderme.mapper.qualifier;

import com.alfauz.orderme.enumeration.RoleName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class RoleQualifier {

    @Named("nameModelToEntity")
    public RoleName name(final String roleName) {
        return RoleName.fromValue(roleName);
    }

    @Named("nameEntityToModel")
    public String name(final RoleName roleName) {
        return roleName.getValue();
    }
}
