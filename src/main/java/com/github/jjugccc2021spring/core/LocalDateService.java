package com.github.jjugccc2021spring.core;

import java.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalDateService {
  @Bean
  public LocalDate now() {
    return LocalDate.now();
  }
}
