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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barath.contacts.exceptions.ApiException;
import com.barath.contacts.util.AuthRequest;
import com.barath.contacts.util.JwtUtil;

@RestController
@RequestMapping("/api/users")
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
	
	@PostMapping("register")
	public ResponseEntity<CustomUser> register(@RequestBody CustomUser customUser) {
		String encodedString = BCrypt.hashpw(customUser.getPassword(), BCrypt.gensalt());
		customUser.setPassword(encodedString);	
		return new ResponseEntity<CustomUser>(customUserService.saveCustomUser(customUser), HttpStatus.CREATED);
	}
	
	@PostMapping("login")
	public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest request) {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			throw new ApiException(e.getMessage());
		}
		Map<String, String> response = new HashMap<String, String>();
		String token = jwt.generateToken(request.getUsername());
		response.put("token", token );
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
		
	}
	
	
}
