package com.example.shoppingList.repository;


import com.example.shoppingList.model.entity.UserRoleEntity;
import com.example.shoppingList.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

  UserRoleEntity findByRole(UserRoleEnum role);

}
