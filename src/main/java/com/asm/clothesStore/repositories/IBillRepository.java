package com.asm.clothesStore.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.User;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer>{
	Page<Bill> findByUserOrderByIdDesc(User user,Pageable pageable);
}
