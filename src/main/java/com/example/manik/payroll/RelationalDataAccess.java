package com.example.manik.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RelationalDataAccess implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RelationalDataAccess.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE employees IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE employees(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), role VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> employees = new ArrayList<>();
        employees.add(new Object[]{"John","Woo", "MLS"});
        employees.add(new Object[]{"Jeff","Dean", "MTS"});
        employees.add(new Object[]{"Josh","Bloch", "Content Writer"});
        employees.add(new Object[]{"Josh","Long", "Designer"});

        // Use a Java 8 stream to print out each tuple of the list
        employees.forEach(name -> log.info(String.format("Inserting employee record for %s %s", name[0], name[1], name[2])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO employees(first_name, last_name, role) VALUES (?,?,?)", employees);

        log.info("Querying for employees records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name, role FROM employees WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> {
                    Employee e = new Employee(rs.getString("first_name"), rs.getString("last_name"), rs.getString("role"));
                    e.setId(rs.getLong("id"));
                    return e;
                }
        ).forEach(employee -> log.info(employee.toString()));
    }
}