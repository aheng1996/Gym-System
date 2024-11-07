package com.milotnt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader("Authorization");
		if (jwt != null) {
			Claims claims = Jwts.parser().setSigningKey("secret_key").parseClaimsJws(jwt).getBody();
			request.setAttribute("claims", claims);
		}
		filterChain.doFilter(request, response);
	}
}
