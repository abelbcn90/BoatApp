/**
 * 
 */
package com.owt.BoatApp.config.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.owt.BoatApp.impl.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author abelb
 *
 */
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${boat.app.jwtSecret}")
	private String jwtSecret;

	@Value("${boat.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Value("${boat.app.jwtCookieName}")
	private String jwtCookie;

	/**
	 * 
	 * @param request
	 * @return
	 */
	public String getJwtFromCookies(final HttpServletRequest request) {
		final Cookie cookie = WebUtils.getCookie(request, jwtCookie);

		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param userPrincipal
	 * @return
	 */
	public ResponseCookie generateJwtCookie(final UserDetailsImpl userPrincipal) {
		final String jwt = generateTokenFromUsername(userPrincipal.getUsername());

		return ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
	}

	/**
	 * 
	 * @return
	 */
	public ResponseCookie getCleanJwtCookie() {
		return ResponseCookie.from(jwtCookie, null).path("/api").build();
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getUserNameFromJwtToken(final String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * 
	 * @return
	 */
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	/**
	 * 
	 * @param authToken
	 * @return
	 */
	public boolean validateJwtToken(final String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);

			return true;

		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());

		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());

		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());

		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public String generateTokenFromUsername(final String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key(), SignatureAlgorithm.HS256).compact();
	}
}
