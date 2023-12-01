/**
 * 
 */
package com.owt.BoatApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.owt.BoatApp.config.jwt.JwtUtils;
import com.owt.BoatApp.entity.User;
import com.owt.BoatApp.impl.UserDetailsImpl;
import com.owt.BoatApp.payload.request.LoginRequest;
import com.owt.BoatApp.payload.request.SignupRequest;
import com.owt.BoatApp.payload.response.MessageResponse;
import com.owt.BoatApp.payload.response.UserInfoResponse;
import com.owt.BoatApp.service.UserService;

import jakarta.validation.Valid;

/**
 * 
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private UserService userService;
	private PasswordEncoder encoder;
	private JwtUtils jwtUtils;

	@Autowired
	public AuthController(final AuthenticationManager authenticationManager, final UserService userService,
			final PasswordEncoder encoder, final JwtUtils jwtUtils) {

		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		final ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (this.userService.checkUserExistsByName(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (this.userService.checkUserExistsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		final User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		this.userService.createUser(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser() {
		final ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
}
