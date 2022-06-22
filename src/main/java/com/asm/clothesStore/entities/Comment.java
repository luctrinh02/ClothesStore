package com.asm.clothesStore.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.asm.clothesStore.entityListeners.CommentListener;

@EntityListeners(CommentListener.class)
@Entity
@Table(name="comments")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommentPK id;

	@Lob
	private String content;

	private Integer rate;

	@ManyToOne
	@JoinColumn(insertable = false,updatable = false)
	private Clothes clothes;

	@ManyToOne
	@JoinColumn(name="userId",insertable = false,updatable = false)
	private User user;

	public Comment() {
	}

	public CommentPK getId() {
		return this.id;
	}

	public void setId(CommentPK id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Clothes getClothes() {
		return this.clothes;
	}

	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", rate=" + rate + "]";
	}
	
}
