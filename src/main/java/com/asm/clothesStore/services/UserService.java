package com.asm.clothesStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.repositories.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserRepository repository;
	public User getById(Integer id) {
		Optional<User> optional=repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	public User getByEmail(String email) {
		Optional<User> optional=repository.findByEmailAndStatus(email,0);
		return optional.isPresent()?optional.get():null;
	}
	public User add(User user) {
		return repository.save(user);
	}
	public List<User> getAll(){
		return repository.findAll();
	}
	public Page<User> getPage(String name,Integer page){
		return repository.findByNameLike("%"+name+"%", PageRequest.of(page, 10));
	}
}
