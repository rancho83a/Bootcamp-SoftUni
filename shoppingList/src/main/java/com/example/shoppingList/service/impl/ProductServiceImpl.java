package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.ProductEntity;
import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.repository.ProductRepository;
import com.example.shoppingList.service.CategoryService;
import com.example.shoppingList.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;

        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {

        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);
        product.setCategory(categoryService.findCategoryByCategoryNameEnum(productServiceModel.getCategory()));
        this.productRepository.save(product);
    }
}
