package com.fb2devs.slopsitebackend.model; 

import jakarta.persistence.*;

@Entity //this marks our class as a JPA (our ORM) entity
@Table(name="students")
public class Student {
  @Id //marks as primary key of entity
  @GeneratedValue(strategy = GenerationType.IDENTITY) //indicating this value is generated by db IDENTITY column
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;
}

// poop bro
// we gotta add setter/getter/constructors
// we can use Lomboc to do this 
// override stuff???
// look at Trae chat
//chatgpt name - mainchat4slopsite
