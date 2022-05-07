package com.github.jjugccc2021spring.assertion;

public class Person {
  private long id;
  private String name;
  private int age;

  public Person(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
