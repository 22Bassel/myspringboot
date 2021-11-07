package com.first.project.controllers;

import com.first.project.services.MyUserDetailsService;
import com.first.project.services.jwt;
import com.sun.xml.internal.ws.api.message.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class jwtfilter extends OncePerRequestFilter {



    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private jwt jwt;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authheader=null ;


        Cookie[] cookies =request.getCookies();
        if(cookies != null ){
            for(Cookie c : cookies){
                if(c.getName().equals("Authorization")){
                    authheader="Bearer "+ c.getValue();
                }
            }
        }


        String username = null;
        String jwtstring = null;

            if (authheader != null && authheader.startsWith("Bearer ")) {
                jwtstring = authheader.substring(7);
                username = jwt.extractusername(jwtstring);


            }
            else if(authheader != null) {
                jwtstring = authheader;
                username = jwt.extractusername(jwtstring);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);


                if (jwt.validatetoken(jwtstring, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            }
            filterChain.doFilter(request, response);

    }




}
