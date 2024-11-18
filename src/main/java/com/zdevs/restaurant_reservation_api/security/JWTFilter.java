package com.zdevs.restaurant_reservation_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;


// Este filtro es importante para manejar la seguridad de la aplicación,
// ya que asegura que cada solicitud HTTP esté autenticada y autorizada correctamente usando tokens JWT.
@RequiredArgsConstructor

public class JWTFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // Extrae el token JWT: Obtiene el token de la cabecera de autorización de la solicitud HTTP.
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        // Verifica y procesa el token: Si el token es válido, se obtiene la autenticación del usuario a partir del token.
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);

            // Establece la autenticación en el contexto de seguridad: Configura la autenticación en el contexto de seguridad para la solicitud actual, permitiendo la autorización basada en el token.
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continúa con la cadena de filtros: Permite que la solicitud siga su curso en la cadena de filtros de Spring Security.
        chain.doFilter(request, response);
    }

}
