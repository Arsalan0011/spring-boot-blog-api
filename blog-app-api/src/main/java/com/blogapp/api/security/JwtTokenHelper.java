package com.blogapp.api.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtTokenHelper {
    public static final long JWT_TOKEN_VALIDITY =5 * 60 * 60;
    private String secret="JwtTokenKey";
    // retrieve username from jwttoken
    public String getUsernameFromToken(String token){
        return getClaimFormToken(token, Claims::getSubject);
    }
    // retrieve expiration date from jwt token

    public Date getExpirationDateFromToken(String token){
        return getClaimFormToken(token, Claims::getExpiration);

    }
    public <T> T getClaimFormToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retreiving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    // check if token has expired
    private boolean isTokenExpired(String toke){
        final Date expiration=getExpirationDateFromToken(toke);
        return expiration.before(new Date());
    }
    // generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    //while creating token
    // 1) define calims of token,like issuer, expiration, subject, and id
    // 2) sign the jwt using the hs512 algorithm and secret key.
    // 3) According to jwt compact serialization (https://tools.ietf.org/html//draft-itef-jose-  )
    // compaction of jwt to a url safe string

    private String doGenerateToken(Map<String ,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).
                setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()
                + JWT_TOKEN_VALIDITY *100)).signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    // validate token
    public Boolean validateToken(String token,UserDetails userDetails){
        final  String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
