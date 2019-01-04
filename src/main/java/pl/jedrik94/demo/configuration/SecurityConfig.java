package pl.jedrik94.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("jedrzej").password("test123").roles("EMPLOYEE", "ADMIN"))
                .withUser(users.username("milosz").password("test123").roles("EMPLOYEE", "MANAGER"))
                .withUser(users.username("seba").password("test123").roles("EMPLOYEE"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/customer/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")
                .antMatchers("/managerPage/**").hasRole("MANAGER")
                .antMatchers("/adminPage/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/authenticateUser")
                .defaultSuccessUrl("/customer/list")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
