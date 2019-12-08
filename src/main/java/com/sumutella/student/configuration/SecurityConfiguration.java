package com.sumutella.student.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author sumutella
 * @time 12:21 AM
 * @since 12/8/2019, Sun
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSourceLogin;

    public SecurityConfiguration(DataSource dataSourceLogin) {
        this.dataSourceLogin = dataSourceLogin;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/confirm").permitAll()
                    .antMatchers("/my-login").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/my-login")
                    .loginProcessingUrl("/auth-user")
                    .defaultSuccessUrl("/index")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/my-login?logout").invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID").permitAll(); // after logout then redirect to login page;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSourceLogin);
    }
}