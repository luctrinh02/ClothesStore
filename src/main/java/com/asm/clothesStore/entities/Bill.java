package com.asm.clothesStore.entities;
import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.*;

import com.asm.clothesStore.entityListeners.BillListener;

import java.util.Date;
import java.util.List;
@EntityListeners(BillListener.class)
@Entity
@Table(name="bills")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer status;

	@Column(name="total_money")
	private BigDecimal totalMoney;

	@Column(name="user_id")
	private Integer userId;

	@ManyToOne
	@JoinColumn(name="user_id",insertable=false, updatable=false)
	private User user;

	@OneToMany(mappedBy="bill")
	private List<Detailbill> detailbills;
	
	public Bill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Detailbill> getDetailbills() {
		return this.detailbills;
	}

	public void setDetailbills(List<Detailbill> detailbills) {
		this.detailbills = detailbills;
	}

	public Detailbill addDetailbill(Detailbill detailbill) {
		getDetailbills().add(detailbill);
		detailbill.setBill(this);

		return detailbill;
	}

	public Detailbill removeDetailbill(Detailbill detailbill) {
		getDetailbills().remove(detailbill);
		detailbill.setBill(null);

		return detailbill;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", date=" + date + ", status=" + status + ", totalMoney=" + totalMoney + ", userId="
				+ userId + "]";
	}
}
