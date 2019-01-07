package pl.jedrik94.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource securityDataSource;

    @Autowired
    public SecurityConfig(DataSource securityDataSource) {
        this.securityDataSource = securityDataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // All passwords match "qwerty12345"
        auth.jdbcAuthentication().dataSource(securityDataSource);
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
