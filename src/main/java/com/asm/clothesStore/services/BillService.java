package com.asm.clothesStore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.repositories.IBillRepository;

@Service
public class BillService {
	@Autowired
	private IBillRepository repository;
	public Bill add(Bill bill) {
		return repository.save(bill);
	}
	public Page<Bill> getByUser(User user,int page) {
		return repository.findByUserOrderByIdDesc(user,PageRequest.of(page, 10));
	}
	public Bill getById(Integer id){
		Optional<Bill> optional=repository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	public Page<Bill> getAll(int page){
	 	return repository.findAll(PageRequest.of(page, 10,Sort.by("id").descending()));
	}
}
