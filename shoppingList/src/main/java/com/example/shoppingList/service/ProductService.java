package com.example.shoppingList.service;


import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.model.service.ProductServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> getAllProductsByCategory(CategoryEnum food);

    BigDecimal getTotalSum();

    void buyProduct(Long id);

    void buyAll();
}
