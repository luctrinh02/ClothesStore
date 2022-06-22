package com.asm.clothesStore.entities;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class CartPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private Integer userId;

	@Column(name="clothes_id", insertable=false, updatable=false)
	private Integer clothesId;	

	public CartPK() {
	}
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getClothesId() {
		return this.clothesId;
	}
	public void setClothesId(Integer clothesId) {
		this.clothesId = clothesId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CartPK)) {
			return false;
		}
		CartPK castOther = (CartPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.clothesId == castOther.clothesId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.clothesId;
		
		return hash;
	}
	
	public CartPK(Integer userId, Integer productId) {
		super();
		this.userId = userId;
		this.clothesId = productId;
	}
	
	@Override
	public String toString() {
		return "CartPK [userId=" + userId + ", clothesId=" + clothesId + "]";
	}
	
}
