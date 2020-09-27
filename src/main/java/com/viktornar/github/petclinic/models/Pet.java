package com.viktornar.github.petclinic.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
@Data
@EqualsAndHashCode(callSuper = false, exclude = { "owner", "visits" })
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends BaseEntity {
    @Column
    @NotEmpty
    private String name;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('CAT', 'DOG', 'FISH', 'REPTILES', 'UNKNOWN')", nullable = false)
    private KindName kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "petId")
    private Set<Visit> visits = new HashSet<>();

    public enum KindName {
        CAT, DOG, FISH, REPTILES, UNKNOWN
    }
}
