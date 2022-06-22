package com.asm.clothesStore.entities;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class CommentPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Integer userId;

	@Column(name="clothes_id", insertable=false, updatable=false)
	private Integer clothesId;

	public CommentPK() {
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
		if (!(other instanceof CommentPK)) {
			return false;
		}
		CommentPK castOther = (CommentPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.clothesId == castOther.clothesId);
	}
	
	public CommentPK(Integer userId, Integer productId) {
		super();
		this.userId = userId;
		this.clothesId = productId;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.clothesId;
		
		return hash;
	}
	@Override
	public String toString() {
		return "CommentPK [userId=" + userId + ", clothesId=" + clothesId + "]";
	}
	
}