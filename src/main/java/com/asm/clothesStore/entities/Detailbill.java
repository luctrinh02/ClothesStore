package com.asm.clothesStore.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.asm.clothesStore.entityListeners.DetailBillListener;
@EntityListeners(DetailBillListener.class)
@Entity
public class Detailbill implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetailbillPK id;

	private Integer amount;

	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name="billId",insertable = false,updatable = false)
	private Bill bill;

	@ManyToOne
	@JoinColumn(insertable = false,updatable = false)
	private Clothes clothes;

	public Detailbill() {
	}

	public DetailbillPK getId() {
		return this.id;
	}

	public void setId(DetailbillPK id) {
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

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Clothes getClothes() {
		return this.clothes;
	}

	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}

	@Override
	public String toString() {
		return "Detailbill [id=" + id + ", amount=" + amount + ", price=" + price + "]";
	}
	
}
