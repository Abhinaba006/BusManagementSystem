package nrifintech.busMangementSystem.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import nrifintech.busMangementSystem.JwtTokenUtil;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.exception.UnauthorizedAction;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.repositories.UserRepo;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			
			String authorizationHeader = request.getHeader("Authorization");
			String url = request.getRequestURI();
			if (url.contains("login") || url.contains("createAdmin")) {
				filterChain.doFilter(request, response);
				return;
			}
			String token = authorizationHeader;
			String payload = jwtTokenUtil.extractUsername(token);
			int userId = Integer.parseInt(payload);
			int type = jwtTokenUtil.extractType(token);
			if (userService.getUser(userId) != null) {
				if(url.contains("secure") && type==0) throw new UnauthorizedAction("trying to access admin role", "user");
				filterChain.doFilter(request, response);
				return;
			}

		} catch (UnauthorizedAction e) {
			ObjectMapper mapper = new ObjectMapper();
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(mapper.writeValueAsString(new ApiResponse(e.getMessage(), false)));
		}
	}
}
