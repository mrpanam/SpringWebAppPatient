package com.example.yousfi_patient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.security.MessageDigest;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    //defini ou chercher les utilisateurs ( memoire, bdd, ldap ...)
    //{noop} dit a spring de ne pas utiliser bcrypt pour encoder le mdp
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password("{noop}1234").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","USER");*/

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, actif from users where username=?")
                .authoritiesByUsernameQuery("select username , role from users_roles where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //si rien avec form login utilise la page de spring par defaut
        http.formLogin();
        //http.httpBasic();
        //http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
        //http.authorizeRequests().antMatchers("/login**/**","/ressources**","/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }

}
