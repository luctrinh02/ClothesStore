package com.asm.clothesStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.clothesStore.entities.Category;
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}
