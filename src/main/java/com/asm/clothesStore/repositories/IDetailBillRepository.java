package com.asm.clothesStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.Detailbill;
import com.asm.clothesStore.entities.DetailbillPK;
@Repository
public interface IDetailBillRepository extends JpaRepository<Detailbill, DetailbillPK>{
	List<Detailbill> findByBill(Bill bill);
}
