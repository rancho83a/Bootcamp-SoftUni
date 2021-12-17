package com.example.shoppingList.model.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;



  public Long getId() {
    return id;
  }

  public BaseEntity setId(Long id) {
    this.id = id;
    return this;
  }


}
