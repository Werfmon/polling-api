package cz.domin.pollingapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import cz.domin.pollingapi.constants.SecurityConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerToken = request.getHeader("Authorization");

        if(headerToken != null && headerToken.startsWith("Bearer")) {
            try {
                String token = headerToken.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                doFilter(request, response, filterChain);
            } catch (Exception exception) {
                System.out.println("Something went wrong in login");
                doFilter(request, response, filterChain);
            }
        } else {
            doFilter(request, response, filterChain);
        }
    }
}
