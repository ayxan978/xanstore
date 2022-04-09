package com.project.xanstore.repository;


import com.project.xanstore.model.CategoryModel;
import com.project.xanstore.model.ComentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentRepository extends JpaRepository<ComentModel,Integer> {

}
