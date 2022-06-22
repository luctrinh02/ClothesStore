package com.asm.clothesStore.dto;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.asm.clothesStore.entities.Category;

public class CLothesDTO {
	private Integer id;
	@NotBlank(message = "Không bỏ trống số lượng")
	@PositiveOrZero(message = "Số lượng là số nguyên lớn hơn 0")
	@Size(max = 6,message = "Số lượng quá lớn")
	private String amount;
	@NotBlank(message = "Không bỏ trống màu")
	private String color;
	private String description;
	@PositiveOrZero(message = "Giảm giá là số nguyên lớn hơn 0")
	@Max(value = 100,message = "Giảm giá không quá 100")
	private String discount;
	@NotBlank(message = "Không bỏ trống tên")
	private String name;
	@PositiveOrZero(message = "Số lượng là số nguyên lớn hơn 0")
	@Size(max = 10,message = "Đơn giá quá lớn")
	private String price;
	private String size;
	private String status;
	private Integer tag;
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
}
