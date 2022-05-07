package com.github.jjugccc2021spring.assertion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PersolListTest {
  private PersonService personService = new PersonService();

  @Test
  void junitデフォルトアサーション() {
    List<Person> actual = personService.ranking();

    List<Person> taroFilterdActual =
        actual.stream()
            .filter(person -> person.getName().equals("taro") && person.getAge() == 20)
            .toList();
    assertEquals(1, taroFilterdActual.size());

    List<Person> hanakoFilterdActual =
        actual.stream()
            .filter(person -> person.getName().equals("hanako") && person.getAge() == 25)
            .toList();
    assertEquals(1, hanakoFilterdActual.size());
  }

  @Test
  void assertjアサーション() {
    List<Person> actual = personService.ranking();

    Person taro = new Person(0L, "taro", 20);
    Person hanako = new Person(0L, "hanako", 25);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorOnFields("name", "age")
        .contains(taro, hanako);
  }
}
