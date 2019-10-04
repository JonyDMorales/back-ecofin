package com.crowdfunding.ecofin.configures;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

    private final String HEADER = "Authorization";
    private PrivateKey privateKey;
    //private String SECRET = "Stratos-trabaja-para-ecofin-jonatan";

    public void uploadKey(){
        Path path = Paths.get("Private.key");
        try{
            byte[] bytes = Files.readAllBytes(path);
            PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = kf.generatePrivate(ks);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if(privateKey == null) {
                uploadKey();
            }
            if (existeJWTToken(request)) {
                Claims claims = validateToken(request);
                if (claims.get("name") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER);
        return Jwts.parser().setSigningKey(privateKey).parseClaimsJws(jwtToken).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        String id = (String) claims.get("name");
        //List<String> roles = (List) claims.get("authorities");
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        /*
        for (String rol : roles){
            grantedAuthority.add (new SimpleGrantedAuthority (rol));
        }
        */
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(id,null, grantedAuthority);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean existeJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null)
            return false;
        return true;
    }

}
