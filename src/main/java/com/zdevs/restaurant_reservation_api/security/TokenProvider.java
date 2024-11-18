package com.zdevs.restaurant_reservation_api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class TokenProvider {

    // Inyecta el valor del secreto JWT desde la configuración.
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Inyecta el tiempo de validez del token JWT en segundos.
    @Value("${jwt.validity-in-seconds}")
    private long jwtValidityInSeconds;

    // Almacena la clave generada a partir del secreto JWT.
    private Key key;

    // Se utiliza para analizar y verificar tokens JWT.
    private JwtParser jwtParser;

    // Inicializa la clave y el parser JWT con la clave secreta.
    // La clave se deriva del secreto configurado y se utiliza para firmar y verificar los tokens JWT.
    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build();
    }

    // Generar tokens JWT:
    // Al autenticar a un usuario, el TokenProvider crea un token JWT que incluye
    // información sobre el usuario, como su nombre y rol.
    // Este token se utiliza para identificar y autenticar al usuario en solicitudes
    // futuras sin necesidad de que el usuario inicie sesión nuevamente.
    public String createAccessToken(Authentication authentication) {
        String role = authentication
                .getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getAuthority();

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim("role", role)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + jwtValidityInSeconds * 1000))
                .compact();
    }

    // Obtener autenticación:
    // Extrae la información del token JWT, como el nombre de usuario y el rol,
    // y crea un objeto de autenticación que se utiliza para verificar
    // los permisos del usuario en la aplicación.
    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        String role = claims.get("role").toString();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    // Validar tokens JWT:
    // Al recibir una solicitud, el TokenProvider verifica la validez del token JWT.
    // Esto incluye comprobar si el token no ha sido alterado y si no ha expirado.
    // Si el token es válido, se extrae la información de autenticación del usuario del token.
    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}
