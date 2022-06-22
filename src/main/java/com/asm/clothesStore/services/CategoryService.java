package com.asm.clothesStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.clothesStore.entities.Category;
import com.asm.clothesStore.repositories.ICategoryRepository;
@Service
public class CategoryService {
	@Autowired
	private ICategoryRepository repository;
	
	public List<Category> getAll(){
		return repository.findAll();
	}
	public Category getById(Integer id){
		Optional<Category> optional=repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
}
