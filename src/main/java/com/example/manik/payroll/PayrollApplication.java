package com.example.manik.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PayrollApplication {

	public static void main(String[] args) {
//		Employee e = new Employee("ganesh", "mls");
//		System.out.println(e.getId());
		SpringApplication.run(PayrollApplication.class, args);
	}

}
