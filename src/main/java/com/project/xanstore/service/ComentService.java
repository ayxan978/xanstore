package com.project.xanstore.service;

import com.project.xanstore.model.ComentModel;
import com.project.xanstore.model.ProductModel;
import com.project.xanstore.repository.ComentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentService {

    @Autowired
    private final ComentRepository comentRepository;


    public List<ComentModel> getComent(int id) {
        int counter = 1;
        List<ComentModel> comentModelList = comentRepository.findAll();
        List<ComentModel> b = new ArrayList<>();
        for (ComentModel a : comentModelList) {
            if(a.getProductModel().equals(id)) {
                b.add(a);
            }

        }

        return b;
    }


    public ComentService(ComentRepository comentRepository) {
        this.comentRepository = comentRepository;
    }
}
