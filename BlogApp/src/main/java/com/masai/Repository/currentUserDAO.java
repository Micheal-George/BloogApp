package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.CurrentUserSession;

public interface currentUserDAO extends JpaRepository<CurrentUserSession, Integer>{

	CurrentUserSession getByUserId(Integer id);

}
