package com.example.manik.payroll;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER")
@EqualsAndHashCode @ToString @NoArgsConstructor
class Order {

    @Getter @Setter private @Id @GeneratedValue Long id;
    @Getter @Setter private String description;
    @Getter @Setter private Status status;

    Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}