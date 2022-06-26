package learn.todos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                .antMatchers("/api/create_account").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/api/todos", "/api/todos/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/todos").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/todos/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/todos/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").denyAll() // if we get to this point (i.e. nothing has matched) then deny all requests
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

//    @Autowired
//    private PasswordEncoder encoder;

    // This is temporary until we start working with the database.
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        var userBuilder = User.withUsername("user")
//                .password("user").passwordEncoder(password -> encoder.encode(password))
//                .roles("USER");
//
//        var adminBuilder = User.withUsername("admin")
//                .password("admin").passwordEncoder(password -> encoder.encode(password))
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder)
//                .withUser(adminBuilder);
//    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Configure CORS globally versus
        // controller-by-controller.
        // Can be combined with @CrossOrigin.
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}
