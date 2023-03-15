package nrifintech.busMangementSystem.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Disable CSRF and enable CORS
            .csrf().disable()
            .cors().and()
            // Set session creation policy to STATELESS
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // Define the authorization rules
            .authorizeRequests()
                .antMatchers("*").permitAll();

    }
}
