package com.zdevs.restaurant_reservation_api.config;


import com.zdevs.restaurant_reservation_api.security.JWTConfigurer;
import com.zdevs.restaurant_reservation_api.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;

    // Define la configuración de seguridad para la aplicación.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configura CORS (Cross-Origin Resource Sharing) con valores predeterminados,
                // permitiendo a la aplicación web hacer solicitudes a otros dominios de manera segura.
                // CORS controla cómo los recursos web son solicitados desde diferentes dominios.
                .cors(Customizer.withDefaults())

                // Desactiva la protección CSRF (Cross-Site Request Forgery),
                // una medida de seguridad que previene que sitios maliciosos envíen solicitudes no autorizadas en nombre del usuario.
                // CSRF no es necesaria en aplicaciones que utilizan tokens JWT para autenticación.
                .csrf(AbstractHttpConfigurer::disable)

                // Configura las reglas de autorización de solicitudes.
                .authorizeHttpRequests((authz) -> authz
                        // Permite el acceso sin autenticación a las rutas de registro y autenticación.
                        .requestMatchers("/auth/sign-up", "/auth/sign-in").permitAll()
                        // Permite el acceso sin autenticación a las rutas de documentación Swagger.
                        .requestMatchers("/api/v1/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll()
                        // Requiere autenticación para cualquier otra solicitud.
                        .anyRequest()
                        .authenticated()
                )

                // Configura la gestión de sesiones para que no se guarden en el servidor.
                .sessionManagement(h -> h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Añade el JWTConfigurer para manejar la autenticación basada en tokens JWT.
                .with(new JWTConfigurer(tokenProvider), Customizer.withDefaults());

        return http.build();
    }

    // Define el encoder de contraseñas utilizado para cifrar las contraseñas de los usuarios.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
