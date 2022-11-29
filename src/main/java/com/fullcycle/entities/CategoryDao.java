package com.fullcycle.entities;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


public class CategoryDao {
  public Category create(Category category){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("java-grpc");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.createQuery("insert into Category (id, name, description) values (:id, :name, :description)")
      .setParameter("id", category.getId())
      .setParameter("name", category.getName())
      .setParameter("description", category.getDescription())
      .executeUpdate();
    em.getTransaction().commit();
    return em.find(Category.class, category.getId());
  }

  public List<Category> list(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("java-grpc");
    EntityManager em = emf.createEntityManager();
    return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
  }
}