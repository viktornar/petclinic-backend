package com.viktornar.github.petclinic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = false, exclude = "roles")
@NoArgsConstructor
@AllArgsConstructor
@ScriptAssert(
        lang = "javascript",
        script="_this.passwordConfirm === _this.password",
        message = "Password doesn't match",
        reportOn = "passwordConfirm"
)
public class User extends BaseEntity {
    @Column(unique = true)
    private String username;

    @Column
    @NotBlank
    @Length(min=4)
    @JsonIgnore
    private String password;

    @Transient
    @NotBlank
    @Length(min = 4)
    @JsonIgnore
    private String passwordConfirm;

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "user_role", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();
}
