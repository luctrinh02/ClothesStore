package com.asm.clothesStore.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.asm.clothesStore.entityListeners.CategoryListener;

import java.util.List;

@EntityListeners(CategoryListener.class)
@Entity
@Table(name="categorys")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	@OneToMany(mappedBy="category")
	private List<Clothes> clothes;

	public Category() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Clothes> getClothes() {
		return this.clothes;
	}

	public void setClothes(List<Clothes> clothes) {
		this.clothes = clothes;
	}

	public Clothes addClothes(Clothes clothes) {
		getClothes().add(clothes);
		clothes.setCategory(this);

		return clothes;
	}

	public Clothes removeClothes(Clothes clothes) {
		getClothes().remove(clothes);
		clothes.setCategory(null);

		return clothes;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + clothes + "]";
	}
	
}
