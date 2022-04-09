package com.project.xanstore.service;

import com.project.xanstore.model.ProductModel;
import com.project.xanstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }
    public List<ProductModel> getProductOfSearch(String name) {
        int counter = 1;
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductModel> b = new ArrayList<>();
        for (ProductModel a : productModelList) {
         int indexM =    a.getName().toLowerCase().indexOf(name);
            if(indexM == - 1) {
            } else {
                a.setIdImg(counter);
                b.add(a);
                counter++;
            }


        }

        return b;
    }
    public ProductModel getProduct(int id) {
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductModel> b = new ArrayList<>();
        for (ProductModel a : productModelList) {
            if (a.getId() == (id)) {
                return  a;
            }
        }

        return null;
    }

    public void addProduct(ProductModel productModel) {
        productRepository.save(productModel);
    }
    public List<ProductModel> getProductOfCategory(String category) {
int counter = 1;
        List<ProductModel> productModelList = productRepository.findAll();
List<ProductModel> b = new ArrayList<>();
        for (ProductModel a : productModelList) {
            if (a.getCategory().equals(category)) {
            a.setIdImg(counter);
                b.add(a);
            counter++;
            }
        }

        return b;
    }
}
