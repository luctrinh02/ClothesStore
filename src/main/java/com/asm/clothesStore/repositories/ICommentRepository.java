package com.asm.clothesStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.clothesStore.entities.Comment;
import com.asm.clothesStore.entities.CommentPK;
import com.asm.clothesStore.entities.Clothes;

public interface ICommentRepository extends JpaRepository<Comment, CommentPK>{
	List<Comment> findByClothes(Clothes clothes);
}
