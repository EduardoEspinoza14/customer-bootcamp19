package com.nttdata.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Class CustomerApplication.
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class CustomerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }

}
