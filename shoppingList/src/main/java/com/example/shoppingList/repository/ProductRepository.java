package com.example.shoppingList.repository;


import com.example.shoppingList.model.entity.ProductEntity;
import com.example.shoppingList.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByCategory_Name(CategoryEnum categoryName);

    @Query("SELECT SUM(p.price) FROM ProductEntity p")
    BigDecimal getTotalSum();

}
