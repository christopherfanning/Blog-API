package dev.cfan.blogapi.security;

import dev.cfan.blogapi.domain.JpaUser;
import dev.cfan.blogapi.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;

// if @EnableWebSecurity not defined, then we're going to get an error with the PasswordEncoder
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JpaUserService jpaUserService;

    @Autowired
    public void setMyUserDetailsService(JpaUserService JPAUserService) {
        this.jpaUserService = JPAUserService;
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * We use the PasswordEncoder that is defined in the Spring Security configuration to encode the password. * @return
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // fetching data for user for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jpaUserService);
    }

    // step2
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // only allowed urls without JWT
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/*").hasAnyRole("AUTHOR", "ADMIN", "USER")
                .antMatchers(HttpMethod.POST,
                        "/api/categories/*/posts/*/comment").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/hello" ).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/auth/login",
                        "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/users",
                        "/users/*",
                        "/api/*",
                        "/api/categories",
                        "/api/categories/*/posts/*").permitAll()
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean  public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JpaUser myUserDetails() {
        return (JpaUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
