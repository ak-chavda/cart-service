//package com.example.cart.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.cart.jwtmodel.AuthenticationRequest;
//import com.example.cart.jwtmodel.AuthenticationResponse;
//import com.example.cart.service.MyUserDetailsService;
//import com.example.cart.util.JwtUtil;
//
//@CrossOrigin
//@RestController
//public class AuthController {
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtUtil jwtTokenUtil;
//
//	@Autowired
//	private MyUserDetailsService userDetailsService;
//
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> createJwtToken(@RequestBody AuthenticationRequest request) throws Exception {
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//		} catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		}
//
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//		return ResponseEntity.ok(new AuthenticationResponse(jwt));
//	}
//
//}
