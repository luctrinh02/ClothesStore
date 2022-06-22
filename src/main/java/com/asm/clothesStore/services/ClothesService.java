package com.asm.clothesStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asm.clothesStore.entities.Category;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.repositories.ICLothesRepository;

@Service
public class ClothesService {
	private ICLothesRepository repository;

	public ClothesService(ICLothesRepository repository) {
		this.repository = repository;
	}

	public List<Clothes> getAll() {
		return repository.findAll();
	}

	public List<Clothes> getByCategory(Category category, int pageIndex) {
		Pageable pageable = PageRequest.of(pageIndex, 4);
		return repository.findByCategoryAndStatusOrderByIdDesc(category,0, pageable).getContent();
	}

	public List<Clothes> getByCategory(Category category) {
		return repository.findByCategoryAndStatusOrderByIdDesc(category,0);
	}

	public List<Clothes> getByCategoryAndTag(Category category, Integer tag) {
		return repository.findByTagAndCategoryAndStatusOrderByIdDesc(tag, category,0);
	}

	public List<Clothes> getByCategoryAndTagAndName(Category category, Integer tag, String name) {
		return repository.findByTagAndCategoryAndNameAndStatusOrderByIdDesc(tag, category, name,0);
	}

	public List<Clothes> getByCategoryAndName(Category category, String name) {
		return repository.findByCategoryAndNameAndStatusOrderByIdDesc(category, name,0);
	}
	public Clothes getById(Integer id) {
		Optional<Clothes> optional=repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	public Clothes add(Clothes p) {
		return repository.save(p);
	}
	public Page<Clothes> getByName(String name,int page){
		return repository.findByNameLikeOderByIdDesc("%"+name+"%", PageRequest.of(page, 10));
	}
}
