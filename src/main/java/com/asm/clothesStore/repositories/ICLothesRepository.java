package com.asm.clothesStore.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asm.clothesStore.entities.Category;
import com.asm.clothesStore.entities.Clothes;
@Repository
public interface ICLothesRepository extends JpaRepository<Clothes, Integer>{
	@Query("select c from Clothes c where c.category=?1 and c.status=?2 and c.amount > 0 order by c.id desc")
	Page<Clothes> findByCategoryAndStatusOrderByIdDesc(Category category,Integer status,Pageable pageable);
	@Query("select c from Clothes c where c.category=?1 and c.status=?2 and c.amount > 0 order by c.id desc")
	List<Clothes> findByCategoryAndStatusOrderByIdDesc(Category category,Integer status);
	@Query("select c from Clothes c where c.tag=?1 and c.category=?2 and c.status=?3 and c.amount > 0 order by c.id desc")
	List<Clothes> findByTagAndCategoryAndStatusOrderByIdDesc(Integer tag,Category category,Integer status);
	@Query("select c from Clothes c where c.tag=?1 and c.category=?2 and c.name like ?3 and c.status=?4 and c.amount > 0 order by c.id desc")
	List<Clothes> findByTagAndCategoryAndNameAndStatusOrderByIdDesc(Integer tag,Category category,String name,Integer status);
	@Query("select c from Clothes c where c.category=?1 and c.name like ?2 and c.status=?3 and c.amount > 0 order by c.id desc")
	List<Clothes> findByCategoryAndNameAndStatusOrderByIdDesc(Category category,String name,Integer status);
	@Query("select c from Clothes c where c.name like ?1 order by c.id desc")
	Page<Clothes> findByNameLikeOderByIdDesc(String name,Pageable pageable);
}
