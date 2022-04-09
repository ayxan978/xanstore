package com.project.xanstore.service;

import com.project.xanstore.model.CategoryModel;
import com.project.xanstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public String getRandomId() {

        List<CategoryModel> categoryModels = categoryRepository.findAll();
        int max = 0;
        int min = 1;
        System.out.println(categoryModels.size());
        int random_int = (int) Math.floor(Math.random() * (categoryModels.size() - min + 1) + min);

 String b = "";
        List<CategoryModel> categoryModels2 = new ArrayList<>() ;
        categoryModels2.add(categoryRepository.getById(random_int));
        for (CategoryModel a : categoryModels2) {
            b = a.getImg_carus();
            System.out.println(b + "  zz");
        }

        return b;
    }

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }


//    public CategoryModel getcat(String nout, String authentication) {
//
//        List<CategoryModel> categoryModels = categoryRepository.getById();
//
//        for (CategoryModel a : categoryModels) {
//            if (a.getBrad().equals(nout))
//                return a;
//        }
//
//        return null;
//    }
}
