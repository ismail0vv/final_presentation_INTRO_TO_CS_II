package com.asyl.ecommerce.resources;

import com.asyl.ecommerce.domain.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asyl.ecommerce.services.ForgotPasswordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Forgot new password")
@CrossOrigin(origins = "*")
@RequestMapping
public class AuthResource {

	@Autowired
	private ForgotPasswordService service;

	@PostMapping("/forgot")
	@ApiOperation(value = "send a new password to email")
	public ResponseEntity<Void> sendNewPassowrd(@RequestBody EmailDTO email) {

		service.sendNewPassword(email.getEmail());
		
		return ResponseEntity.ok().build();
	}
	


}
