package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.ProductEntity;
import com.example.shoppingList.model.entity.UserEntity;
import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.repository.ProductRepository;
import com.example.shoppingList.service.CategoryService;
import com.example.shoppingList.service.ProductService;
import com.example.shoppingList.service.UserService;
import com.example.shoppingList.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService, UserService userService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;

        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {

        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);
        product.setCategory(categoryService.findCategoryByCategoryNameEnum(productServiceModel.getCategory()));
        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> getAllProductsByCategory(CategoryEnum food) {

        return this.productRepository.findAllByCategory_Name(food)
                .stream()
                .map(product-> modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalSum() {
       return this.productRepository.getTotalSum();
    }

    @Override
    public void buyProduct(Long id) {
        ProductEntity productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Sorry, object not found!"));

        this.productRepository.delete(productEntity);
    }

    @Override
    public void buyAll() {
        this.productRepository.deleteAll();
    }

    @Transactional
    @Override
    public boolean isOwner(String username, Long id) {



        Optional<ProductEntity> productOpt = productRepository.findById(id);
        Optional<UserEntity> currentUserOpt = userService.findByUsername(username);

        if (productOpt.isEmpty() || currentUserOpt.isEmpty()) {
            return false;
        }
        UserEntity currentUser = currentUserOpt.get();


        return userService.isAdmin(currentUser);
    }
}
