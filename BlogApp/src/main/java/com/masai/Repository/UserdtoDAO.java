package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.UserDTO;

public interface UserdtoDAO extends JpaRepository<UserDTO, Integer>{

}
