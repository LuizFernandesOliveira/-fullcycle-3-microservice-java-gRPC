package com.fullcycle.services;

import com.fullcycle.entities.CategoryDao;
import com.fullcycle.template.category.Category;
import com.fullcycle.template.category.CategoryList;
import com.fullcycle.template.category.Empty;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import com.fullcycle.template.category.CategoryServiceGrpc;

@GRpcService
public class CategoryService extends CategoryServiceGrpc.CategoryServiceImplBase {
  private CategoryDao categoryDao = new CategoryDao();


  @Override
  public void create(Category request, StreamObserver<Category> responseObserver) {
    var category = categoryDao.create(com.fullcycle.entities.Category.of(request.getId(), request.getName(), request.getDescription()));
    responseObserver.onNext(Category.newBuilder().setId(category.getId()).setName(category.getName()).setDescription(category.getDescription()).build());
    responseObserver.onCompleted();
  }

  @Override
  public void list(Empty request, StreamObserver<CategoryList> responseObserver) {
    var categories = categoryDao.list();
    var categoryList = CategoryList.newBuilder();
    categories.forEach(category -> {
      categoryList.addCategories(Category.newBuilder()
        .setId(category.getId())
        .setName(category.getName())
        .setDescription(category.getDescription())
        .build());
    });
    responseObserver.onNext(categoryList.build());
    responseObserver.onCompleted();
  }
}
