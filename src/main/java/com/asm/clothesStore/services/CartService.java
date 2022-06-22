package com.asm.clothesStore.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.clothesStore.entities.Cart;
import com.asm.clothesStore.entities.CartPK;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.repositories.ICartRepository;
import com.asm.clothesStore.repositories.ICLothesRepository;

@Service
public class CartService {
	@Autowired
	private ICartRepository repository;
	public int addToCart(User user,Clothes clothes,Integer amount) {
		Cart newCart=new Cart();
		CartPK cartPK=new CartPK(user.getId(), clothes.getId());
		Optional<Cart> optional= repository.findById(cartPK);
		if(optional.isPresent()) {
			Cart cart=optional.get();
			if(cart.getAmount()+amount>clothes.getAmount()) {
				return 0;
			}else {
				cart.setAmount(cart.getAmount()+amount);
				repository.save(cart);
				return 1;
			}
		}else {
			newCart.setId(cartPK);
			if(amount>clothes.getAmount()) {
				return 0;
			}else {
				newCart.setUser(user);
				newCart.setClothes(clothes);
				newCart.setAmount(amount);
				newCart.setPrice(new BigDecimal(clothes.getPrice().intValue() * (100 - clothes.getDiscount()) / 100));
				repository.save(newCart);
				return 1;
			}
		}
	}
	public void removeProductCard(CartPK cartPK){
		Optional<Cart> optional= repository.findById(cartPK);
		if(optional.isPresent()) {
			repository.deleteById(cartPK);
		}
	}
	public List<Cart> getByUser(User user){
		return repository.findByUser(user);
	}
	public void deleteCard(User user) {
		List<Cart> carts=this.getByUser(user);
		repository.deleteAll(carts);
	}
	public List<Cart> getByClothes(Clothes clothes){
		return repository.findByClothes(clothes);
	}
	public Cart add(Cart cart) {
		return repository.save(cart);
	}
}
