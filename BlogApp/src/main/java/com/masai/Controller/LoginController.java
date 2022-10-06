package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Model.Post;
import com.masai.Model.User;
import com.masai.Model.UserDTO;
import com.masai.Service.UserService;

@RestController
@RequestMapping("/api/posts")
public class LoginController {
	@Autowired
	UserService uservice;
	
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody User user )
	{
		return new ResponseEntity<String>(uservice.UserSignUp(user),	HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> LogIn(@RequestBody UserDTO ud )
	{
		return new ResponseEntity<String>(uservice.UserLogIn(ud),	HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<String> Logout(@PathVariable int id )
	{
		return new ResponseEntity<String>(uservice.UserLogOut(id),	HttpStatus.OK);
	}


}
