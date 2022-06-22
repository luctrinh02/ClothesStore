package com.asm.clothesStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.clothesStore.entities.Cart;
import com.asm.clothesStore.entities.CartPK;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;

public interface ICartRepository extends JpaRepository<Cart, CartPK>{
	List<Cart> findByUser(User user);
	Long countByUser(User user);
	List<Cart> findByClothes(Clothes clothes);
}
