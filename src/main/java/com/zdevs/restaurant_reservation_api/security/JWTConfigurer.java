package com.zdevs.restaurant_reservation_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//La clase JWTConfigurer integra el filtro JWTFilter en el ciclo de seguridad de Spring Security.
// Esto garantiza que las solicitudes HTTP sean autenticadas usando tokens JWT antes de ser procesadas, proporcionando
// una capa de seguridad adicional para proteger los recursos de la aplicación.

//// Permite agregar filtros personalizados a la seguridad de la aplicación
@RequiredArgsConstructor
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JWTFilter jwtFilter = new JWTFilter(tokenProvider);
        //Esto asegura que el JWTFilter procese los tokens JWT y establezca la autenticación antes de que se
        // maneje cualquier autenticación estándar de nombre de usuario y contraseña.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
