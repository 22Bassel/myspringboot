package com.first.project;

import com.first.project.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class securityconfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myuserdetailsservice;


    @Autowired
    private Filter jwtfilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myuserdetailsservice);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
 .disable()
 .authorizeRequests()
 .antMatchers("/css/**","/templates/**","/js/**","/auth","/showadduser","/checkadduser").permitAll().anyRequest().authenticated()

            // "/checkaddcar","/checkdeletecar","/checkeditcar"
         .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

    }


 @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }



    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
