package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.CategoryEntity;
import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.repository.CategoryRepository;
import com.example.shoppingList.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategory() {

        Arrays.stream(CategoryEnum.values())
                .forEach(categoryEnum -> {

                    CategoryEntity categoryEntity = new CategoryEntity()
                            .setName(categoryEnum)
                            .setDescription("This is description for category:"+categoryEnum);
                    categoryRepository.save(categoryEntity);

                });


    }

    @Override
    public CategoryEntity findCategoryByCategoryNameEnum(CategoryEnum category) {
        return this.categoryRepository.findByName(category);
    }
}
