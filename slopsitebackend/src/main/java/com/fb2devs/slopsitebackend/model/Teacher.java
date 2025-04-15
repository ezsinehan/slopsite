package com.fb2devs.slopsitebackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  // required no-arg constructor
  public Teacher() {
  }

  // constructor with fields
  public Teacher(String username, String password, String name) {
    this.username = username;
    this.password = password;
    this.name = name;
  }

  // getters/setters next (or Lombok later)
}
