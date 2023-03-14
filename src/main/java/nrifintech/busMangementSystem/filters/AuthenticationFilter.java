	package nrifintech.busMangementSystem.filters;
	
	import java.io.IOException;
	
	import javax.servlet.FilterChain;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;
	import org.springframework.web.filter.OncePerRequestFilter;
	
	import nrifintech.busMangementSystem.JwtTokenUtil;
	import nrifintech.busMangementSystem.Service.interfaces.UserService;
	import nrifintech.busMangementSystem.exception.UnauthorizedAction;
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
	
			// Get the custom header from the request
	//		 String authorizationHeader  = request.getHeader("x-auth-token");
			String authorizationHeader = request.getHeader("Authorization");
			System.out.println("\n--------------------------------\n----------------------\n");
			System.out.println("authorizationHeader: " + authorizationHeader);
			System.out.println("\n--------------------------------\n----------------------\n");
	
			String url = request.getRequestURI();
			if (url.contains("login")) {
				filterChain.doFilter(request, response);
				return;
			}
	
			String token = authorizationHeader;
			// // Use the token for authentication/authorization purposes
			System.out.println("\n--------------------------------\n----------------------\n");
			String payload = jwtTokenUtil.extractUsername(token);
			int userId = Integer.parseInt(payload);
			int type = jwtTokenUtil.extractType(token);
			System.out.println(userId + " " + type);
			if (userService.getUser(userId) != null) {
				// if(userRepo.findById(userId).isEmpty()) throw new UnauthorizedAction("invalid
				// credentials", "undefiened user");
				filterChain.doFilter(request, response);
				return;
			}
			if (url.contains("create") && type == 0 && !url.contains("ticket/create")) {
				throw new UnauthorizedAction("Admin permission needed", "undefiened user");
			}
			System.out.println(userId);
			System.out.println("\n--------------------------------\n----------------------\n");
	
			// System.out.println(authorizationHeader);
	
	//		System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
	//		if (url.contains("/admin"))
	//			userType = 1;
	
			/*
			 * if ((userType == 0 && !userService.checkUser(userName, password))) throw new
			 * UnauthorizedAction("Missing or invalid Custom-Header header",
			 * "undefiened user"); if ((userType == 1 && !userService.checkAdmin(userName,
			 * password))) throw new
			 * UnauthorizedAction("Missing or invalid Custom-Header header",
			 * "undefiened user");
			 */
	
			throw new UnauthorizedAction("Invalid token provided", "undefiened user");
		}
	}
