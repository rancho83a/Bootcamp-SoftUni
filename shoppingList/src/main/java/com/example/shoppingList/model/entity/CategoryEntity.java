package com.example.shoppingList.model.entity;

import com.example.shoppingList.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class CategoryEntity extends BaseEntity{

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
