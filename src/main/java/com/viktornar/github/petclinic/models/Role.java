package com.viktornar.github.petclinic.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = false, exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(
      name = "name",
      columnDefinition = "ENUM('ADMIN','EDITOR', 'USER', 'ANONYMOUS')",
      nullable = false
    )
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> users;

    public enum RoleName {
        ADMIN,
        EDITOR,
        USER,
        ANONYMOUS
    }
}