package com.RGR.Auction.models;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="name")
    private String name;

    public CategoryModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
