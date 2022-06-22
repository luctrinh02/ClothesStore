package com.asm.clothesStore.entities;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class DetailbillPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Integer billId;

	@Column(name="clothes_id", insertable=false, updatable=false)
	private Integer clothesId;

	public DetailbillPK() {
	}
	public Integer getBillId() {
		return this.billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
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
		if (!(other instanceof DetailbillPK)) {
			return false;
		}
		DetailbillPK castOther = (DetailbillPK)other;
		return 
			(this.billId == castOther.billId)
			&& (this.clothesId == castOther.clothesId);
	}
	
	public DetailbillPK(Integer billId, Integer productId) {
		super();
		this.billId = billId;
		this.clothesId = productId;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.billId;
		hash = hash * prime + this.clothesId;
		
		return hash;
	}
	@Override
	public String toString() {
		return "DetailbillPK [billId=" + billId + ", clothesId=" + clothesId + "]";
	}
	
}
