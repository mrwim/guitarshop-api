package nl.inholland.guitarshopapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import nl.inholland.guitarshopapi.model.Role;
import nl.inholland.guitarshopapi.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${application.token.secret-key")
    private String secret;

    @Value("${application.token.validity}")
    private long validityInMicroseconds;

    private MemberDetailsService memberDetailsService;

    public JwtTokenProvider(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String memberName, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(memberName);
        claims.put("auth",
                roles
                        .stream()
                        .map(r -> new SimpleGrantedAuthority(r.getAuthority()))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityInMicroseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
