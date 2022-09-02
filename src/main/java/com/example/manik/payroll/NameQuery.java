package com.example.manik.payroll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
public class NameQuery {
    @Getter @Setter private String name;
    @Getter @Setter private int age, count;
}
