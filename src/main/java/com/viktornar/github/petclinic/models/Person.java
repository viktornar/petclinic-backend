package com.viktornar.github.petclinic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Person extends BaseEntity {
    @Column(name = "first_name")
    @NotEmpty
    private String firstName;
    @Column
    @NotEmpty
    private String lastName;
}
