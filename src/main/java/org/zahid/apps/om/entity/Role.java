package org.zahid.apps.om.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.zahid.apps.om.enumeration.RoleName;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "om", uniqueConstraints = {
        @UniqueConstraint(name = "role_name_uk", columnNames = {"name"})
})
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Auditable<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated(EnumType.STRING)
  @NaturalId
  @Column(length = 60)
  private RoleName name;
}
