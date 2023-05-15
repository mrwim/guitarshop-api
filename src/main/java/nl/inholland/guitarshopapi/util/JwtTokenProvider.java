package nl.inholland.guitarshopapi.util;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import nl.inholland.guitarshopapi.model.Role;
import nl.inholland.guitarshopapi.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    // Link to the documentation of the used JWT library:
    // https://github.com/jwtk/jjwt

    @Value("${server.ssl.key-store}")
    private String keystore;

    @Value("${server.ssl.key-store-password}")
    private String password;

    @Value("${server.ssl.key-alias}")
    private String alias;

    // We'll get the private key from the key store
    private PrivateKey privateKey;

    @Value("${application.token.validity}")
    private long validityInMicroseconds;

    private final MemberDetailsService memberDetailsService;

    public JwtTokenProvider(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @PostConstruct
    protected void init() throws Exception {
        keystore = keystore.replace("classpath:", "");
        privateKey = (PrivateKey) KeyHelper.getPrivateKey(alias, keystore, password);
    }

    public String createToken(String username, List<Role> roles) throws JwtException {

        /* The token will look something like this

        {
          "sub": "admin",
          "auth": [
            {
              "role": "ROLE_ADMIN"
            }
          ],
          "iat": 1684073744,
          "exp": 1684077344
        }

        */

        // We create a new Claims object for the token
        // The username is the subject
        Claims claims = Jwts.claims().setSubject(username);

        // And we add an array of the roles to the auth element of the Claims
        // Note that we only provide the role as information to the frontend
        // The actual role based authorization should always be done in the backend code
        claims.put("auth",
                roles
                        .stream()
                        .map(Role::name)
                        .toList());

        // We decide on an expiration date
        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityInMicroseconds);

        // And finally, generate the token and sign it. .compact() then turns it into a string that we can return.
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(privateKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        // We will get the username from the token
        // And then get the UserDetails for this user from our service
        // We can then pass the UserDetails back to the caller
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(privateKey).build().parseClaimsJws(token);
            String username = claims.getBody().getSubject();
            UserDetails userDetails = memberDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Bearer token not valid");
        }
    }
}