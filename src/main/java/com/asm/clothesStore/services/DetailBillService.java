package com.asm.clothesStore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.Detailbill;
import com.asm.clothesStore.repositories.IDetailBillRepository;

@Service
public class DetailBillService {
	@Autowired
	private IDetailBillRepository repository;
	
	public Detailbill add(Detailbill detailbill) {
		return repository.save(detailbill);
	}
	public List<Detailbill> getByBill(Bill bill){
		return repository.findByBill(bill);
	}
}
