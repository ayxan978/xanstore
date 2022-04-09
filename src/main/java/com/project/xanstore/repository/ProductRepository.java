package com.project.xanstore.repository;


import com.project.xanstore.model.CategoryModel;
import com.project.xanstore.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel,Integer> {

}
