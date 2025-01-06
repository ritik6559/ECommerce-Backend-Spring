package com.ritik.dreamshop.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritik.dreamshop.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany( mappedBy = "category")
    private List<Product> products;

    public Category(String name) {
        this.name = name;
    }
}
