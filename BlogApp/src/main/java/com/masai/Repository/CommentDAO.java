package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.Comment;

public interface CommentDAO extends JpaRepository<Comment, Long>{

	List<Comment> findByPostId(Long pid);

}
