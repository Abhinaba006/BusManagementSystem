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
			
			
////	
			String token = authorizationHeader;
			String payload = jwtTokenUtil.extractUsername(token);
			int userId = Integer.parseInt(payload);
			int type = jwtTokenUtil.extractType(token);
//			
//	
			System.out.println("\n--------------------------------\n----------------------\n");
			System.out.println("USERID136 TYPE0: " + userId+" "+type);
			System.out.println("\n--------------------------------\n----------------------\n");
			

			
			if (userService.getUser(userId) != null) {
				filterChain.doFilter(request, response);
				return;
			}
			
				
			throw new UnauthorizedAction("Invalid token provided", "undefiened user");
			
//			
		}
	}
