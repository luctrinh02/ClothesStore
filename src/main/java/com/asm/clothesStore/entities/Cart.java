package com.asm.clothesStore.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.asm.clothesStore.entityListeners.CartListener;
@EntityListeners(CartListener.class)
@Entity
@Table(name = "carts")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CartPK id;

	private int amount;

	private BigDecimal price;
	@ManyToOne
	@JoinColumn(insertable = false,updatable = false)
	private Clothes clothes;

	@ManyToOne
	@JoinColumn(insertable = false,updatable = false)
	private User user;

	public Cart() {
	}

	public CartPK getId() {
		return this.id;
	}

	public void setId(CartPK id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Clothes getClothes() {
		return clothes;
	}

	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", amount=" + amount + ", price=" + price + ", clothes=" + clothes + "]";
	}
	
	
}
