package com.first.project.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class jwt {

    private String secret_key="secret";



    public String extractusername(String token){

        return extractClaim(token, Claims::getSubject);
    }


    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }



    public <T> T extractClaim(String token, Function<Claims,T> claimsResolve){
        final Claims claims=extractallClaim(token);
        return claimsResolve.apply(claims);
    }

    private Claims extractallClaim(String token){

        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
    }


    private Boolean isTokenExpird(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public String generatetoken(UserDetails userDetails){

        Map<String,Object> claims=new HashMap<>();
        return createtoken(claims,userDetails.getUsername());
    }

    private String createtoken(Map<String,Object> claims,String Username){
        return Jwts.builder().setClaims(claims).setSubject(Username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 *60*60*24))
                .signWith(SignatureAlgorithm.HS256 ,secret_key).compact();
    }


    public Boolean validatetoken(String token ,UserDetails userDetails){

        final String username=extractusername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpird(token));

    }


}
