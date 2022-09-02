package com.example.manik.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class APIConsumerService {

    private static final Logger log = LoggerFactory.getLogger(APIConsumerService.class);
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        final String name = "manik";
        return args -> {
            NameQuery quote = restTemplate.getForObject(
                    "https://api.agify.io/?name=" + name, NameQuery.class);
            log.info(quote.toString());
        };
    }
}
