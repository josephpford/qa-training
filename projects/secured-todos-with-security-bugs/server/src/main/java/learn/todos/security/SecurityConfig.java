package learn.todos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtConverter jwtConverter;

    public SecurityConfig(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    // This method allows configuring web based security for specific http requests.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // We're not using HTML forms in our app so disable CSRF (Cross Site Request Forgery).
        http.csrf().disable();

        // This configures Spring Security to allow CORS related requests (such as preflight checks).
        http.cors();

        // The order of the antMatchers() method calls is important
        // as they're evaluated in the order that they're added.
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/todos/*/incomplete", "/api/todos/*/completed", "/api/todos/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/todos").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/todos/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), jwtConverter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
