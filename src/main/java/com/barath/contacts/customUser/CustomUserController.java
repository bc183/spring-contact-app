package com.barath.contacts.customUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barath.contacts.exceptions.ApiException;
import com.barath.contacts.util.AuthRequest;
import com.barath.contacts.util.JwtUtil;

@RestController
@RequestMapping(value = "/api/users")
public class CustomUserController {
	
	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	CustomUserService customUserService;
	
	@GetMapping("")
	public ResponseEntity<List<CustomUser>> getAllUsers() {
		return new ResponseEntity<List<CustomUser>>(customUserService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<CustomUser> getUserByUsername(@PathVariable("username") String username) throws ApiException {
		CustomUser user = customUserService.findUserByUsername(username);
		//System.out.println(username);
		if (user == null) {
			throw new ApiException("User not found check the username");
		}
		
		return new ResponseEntity<CustomUser>(user, HttpStatus.OK);
	}
	
	@PostMapping("register")
	public ResponseEntity<CustomUser> register(@RequestBody CustomUser customUser) throws ApiException {
		CustomUser user = customUserService.findUserByUsername(customUser.getUsername());
		//System.out.println(user.getUsername());
		if (user != null) {
			throw new ApiException("User with this username already exists.");
		}
		user = customUserService.findUserByEmail(customUser.getEmail());
		if (user != null) {
			throw new ApiException("User with this email already exists.");
		}
		String encodedString = BCrypt.hashpw(customUser.getPassword(), BCrypt.gensalt());
		customUser.setPassword(encodedString);	
		return new ResponseEntity<CustomUser>(customUserService.saveCustomUser(customUser), HttpStatus.CREATED);
	}
	
	@PostMapping("login")
	public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest request) throws ApiException 	{
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			throw new ApiException("Username or password is invalid.");
		}
		Map<String, String> response = new HashMap<String, String>();
		String token = jwt.generateToken(request.getUsername());
		response.put("token", token );
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		
	}
	
	
}
