package com.fullcycle.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;
  private String name;
  private String description;

  public static Category of(Integer id, String name, String description){
    Category category = new Category();
    category.setId(id);
    category.setName(name);
    category.setDescription(description);
    return category;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
