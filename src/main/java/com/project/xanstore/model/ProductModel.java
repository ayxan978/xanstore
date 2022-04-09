package com.project.xanstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String cost;
    private String dimensions;
    private String color;
    private String category;
    private String phone;
    private String text;
    private String photo;

    private int idImg;
//


    @OneToMany(mappedBy = "productModel")
    private List<ComentModel> comentModels;
    public void addComent(ComentModel comentModel) {
        System.out.println("a");
        if (comentModels == null) {
            comentModels = new ArrayList<>();
        }
        System.out.println("b");
        comentModels.add(comentModel);
    }

    public List<ComentModel> getComentModels() {
        return comentModels;
    }

    public void setComentModels(List<ComentModel> comentModels) {
        this.comentModels = comentModels;
    }
    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                ", photo='" + photo + '\'' +
                ", idImg=" + idImg +
                '}';
    }
   }
