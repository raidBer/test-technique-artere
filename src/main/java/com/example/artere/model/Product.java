package com.example.artere.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double price;
    private int stock;

    @ManyToOne
    @JsonIgnore
    private Category category;
}
