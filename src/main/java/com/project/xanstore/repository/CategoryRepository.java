package com.project.xanstore.repository;


import com.project.xanstore.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel,Integer> {

}
