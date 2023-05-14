package nl.inholland.guitarshopapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import nl.inholland.guitarshopapi.model.Role;
import nl.inholland.guitarshopapi.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${server.ssl.key-store}")
    private String keystore;

    @Value("${server.ssl.key-store-password}")
    private String password;

    @Value("${application.token.validity}")
    private long validityInMicroseconds;

    @Value("${server.ssl.key-alias}")
    private String alias;

    private MemberDetailsService memberDetailsService;

    private PrivateKey privateKey;

    public JwtTokenProvider(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @PostConstruct
    protected void init() throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        keystore = keystore.replace("classpath:", "");
        privateKey = (PrivateKey) KeyHelper.getPrivateKey(alias, keystore, password);
    }

    public String createToken(String memberName, List<Role> roles) throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
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
                .signWith(SignatureAlgorithm.HS256,
                        privateKey.getEncoded())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = memberDetailsService.loadUserByUsername(
                getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(privateKey.getEncoded()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (!Objects.isNull(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(privateKey.getEncoded()).parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Bearer token not valid");
        }
        return true;
    }
}

