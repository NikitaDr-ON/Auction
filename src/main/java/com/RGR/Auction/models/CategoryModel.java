package com.RGR.Auction.models;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(fetch = FetchType.LAZY)
    private long id;
    @Column(name="name")
    private String Name;

    public CategoryModel(long id, String name) {
        this.id = id;
        Name = name;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
