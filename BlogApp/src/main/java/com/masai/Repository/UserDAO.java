package com.masai.Repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.User;

public interface UserDAO extends JpaRepository<User, Integer>{

	

	User getByMobileNo(@NotNull(message = "Mobile is mandatory") String mobileNo);

}
