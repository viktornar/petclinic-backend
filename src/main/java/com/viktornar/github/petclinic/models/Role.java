package com.viktornar.github.petclinic.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(
      name = "name",
      columnDefinition = "ENUM('ADMIN','EDITOR', 'USER', 'ANONYMOUS')",
      nullable = false
    )
    @Getter
    @Setter
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    @Getter
    @Setter
    private Set<User> users;

    public enum RoleName {
        ADMIN,
        EDITOR,
        USER,
        ANONYMOUS
    }
}