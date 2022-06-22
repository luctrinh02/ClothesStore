package com.asm.clothesStore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.clothesStore.entities.Comment;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.repositories.ICommentRepository;

@Service
public class CommentService {
	@Autowired
	private ICommentRepository repository;
	public Comment add(Comment comment) {
		return repository.save(comment);
	}
	public List<Comment> getByProduct(Clothes clothes){
		return repository.findByClothes(clothes);
	}
}
