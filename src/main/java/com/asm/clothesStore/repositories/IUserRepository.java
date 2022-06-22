package com.asm.clothesStore.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asm.clothesStore.entities.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmailAndStatus(String email,Integer Status);
	@Query("select u from User u where u.fullname like ?1")
	Page<User> findByNameLike(String name,Pageable pageable);
}
