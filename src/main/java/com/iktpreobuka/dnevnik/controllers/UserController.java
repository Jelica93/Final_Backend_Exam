package com.iktpreobuka.dnevnik.controllers;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.entities.UserEntity;
import com.iktpreobuka.dnevnik.entities.dto.UserDTO;
import com.iktpreobuka.dnevnik.repositories.UserRepository;
import com.iktpreobuka.dnevnik.utils.Encryption;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Value("${spring.security.secret-key}")
	private String secretKey;
	
	@Value("${spring.security.token-duration}")
	private Long duration;
	
	
	@RequestMapping(path="/users", method = RequestMethod.GET) 
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String pwd) {
		
		System.out.println(email);
		
		List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
		
		for (UserEntity userEntity : users) {
			System.out.println(userEntity.getEmail() + " <-> " + userEntity.getPassword() + " " + Encryption.validatePassword(pwd, userEntity.getPassword()));
			if (userEntity.getEmail().equals(email) && Encryption.validatePassword(pwd, userEntity.getPassword())) {
				String token = getJWTToken(userEntity);
				UserDTO user = new UserDTO();
				user.setUser(email);
				user.setToken(token);
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>("Email and password do not match", HttpStatus.UNAUTHORIZED);
	}
	
	
	private String getJWTToken(UserEntity user) {
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getName());
		
		String token = Jwts.builder().setId("softtekJWT").setSubject(user.getEmail())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
	}
}

	

