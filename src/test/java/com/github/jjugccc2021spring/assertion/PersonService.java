package com.github.jjugccc2021spring.assertion;

import java.util.List;

public class PersonService {
  public List<Person> ranking() {
    return List.of(
        new Person(1L, "taro", 20),
        new Person(10L, "hanako", 25),
        new Person(30L, "takuya", 24),
        new Person(100L, "motoki", 30));
  }
}
