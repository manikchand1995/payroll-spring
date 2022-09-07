package com.example.manik.payroll;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor @EqualsAndHashCode @ToString
class Employee {

    @Getter @Setter @Id @GeneratedValue
    private Long id;

    @Getter @Setter @NotNull @Size(min=2, max=30)
    private String firstName;

    @Getter @Setter @NotNull @Size(min=2, max=30)
    private String lastName;

    @Getter @Setter
    private String role;

    Employee(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

}