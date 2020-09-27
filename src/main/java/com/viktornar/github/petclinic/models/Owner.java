package com.viktornar.github.petclinic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
@Data
@EqualsAndHashCode(callSuper = false, exclude = "pets")
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends Person {
    @Column
    @NotEmpty
    private String address;

    @Column
    @NotEmpty
    private String city;

    @Column
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Pet> pets = new HashSet<>();
}
