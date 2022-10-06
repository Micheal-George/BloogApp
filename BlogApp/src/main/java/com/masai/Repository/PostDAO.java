package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.Post;

public interface PostDAO extends JpaRepository<Post, Long>{

}
