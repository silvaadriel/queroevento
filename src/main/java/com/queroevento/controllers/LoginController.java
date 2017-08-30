package com.queroevento.controllers;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.queroevento.models.Login;
import com.queroevento.services.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> postLogin(@RequestBody Login login) {

		if (login.getEmail() == null || login.getPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		login.setCreateDate(new Date());
		login.setActive(true);

		return new ResponseEntity<>(loginService.save(login), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login/password/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> putLoginPassword(@RequestBody Login login, @PathVariable Long id) {

		Login existenceLogin = loginService.findOne(id);

		if (existenceLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		existenceLogin.setPassword(login.getPassword());

		return new ResponseEntity<>(loginService.save(login), HttpStatus.OK);
	}

	@RequestMapping(value = "/login/email/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> putLoginEmail(@RequestBody Login login, @PathVariable Long id) {

		Login existenceLogin = loginService.findOne(id);

		if (existenceLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		existenceLogin.setEmail(login.getEmail());

		return new ResponseEntity<>(loginService.save(login), HttpStatus.OK);
	}

	@RequestMapping(value = "/login/active/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> putLoginActive(@RequestBody Login login, @PathVariable Long id) {

		Login existenceLogin = loginService.findOne(id);

		if (existenceLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		existenceLogin.setActive(login.getActive());

		return new ResponseEntity<>(loginService.save(login), HttpStatus.OK);
	}

	// Autenticação de usuário
	@RequestMapping(value = "/login/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Login> authenticatedToken(@RequestBody Login login) throws ServletException {

		Login existenceLogin = loginService.validateLogin(login);

		if (existenceLogin.getExpirationTokenDate() != null
				&& existenceLogin.getExpirationTokenDate().after(new Date())) {

			return new ResponseEntity<>(existenceLogin, HttpStatus.OK);

		} else if (existenceLogin.getExpirationTokenDate() == null
				|| existenceLogin.getExpirationTokenDate().before(new Date())) {

			Date expirationDate = new Date(System.currentTimeMillis() + 240 * 60 * 1000);

			String token = Jwts.builder().setSubject(existenceLogin.getEmail())
					.signWith(SignatureAlgorithm.HS512, "autenticando").setExpiration(expirationDate).compact();

			existenceLogin.setToken(token);
			existenceLogin.setExpirationTokenDate(expirationDate);
		}

		return new ResponseEntity<>(loginService.save(existenceLogin), HttpStatus.OK);
	}

}