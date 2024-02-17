package com.rmc.app.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.headers(headersConfigurer -> headersConfigurer
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
                http.authorizeHttpRequests(
                                auth -> auth

                                                // Permisos categoria
                                                .requestMatchers("/categoria/nuevo/**", "/categoria/editar/**",
                                                                "/categoria/borrar/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers("/categoria/list").permitAll()

                                                // Permisos productos
                                                .requestMatchers("/compra/usuario/**", "/producto/nuevo/**",
                                                                "/producto/editar/**", "/producto/borrar/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers("/compra/list").hasRole("USER")

                                                // Permisos usuario
                                                .requestMatchers("/usuario/list").hasRole("ADMIN")
                                                .requestMatchers("/usuario/nuevo/**").permitAll()
                                                .requestMatchers("/usuario/editar/**", "/usuario/borrar/**")
                                                .hasAnyRole("ADMIN", "USER")

                                                // Permisos valoracion
                                                .requestMatchers("/valoracion/producto/**").permitAll()
                                                .requestMatchers("/valoracion/usuario/**", "/valoracion/nuevo/**",
                                                                "/valoracion/delete/**")
                                                .hasAnyRole("ADMIN", "USER")

                                                // Permisos LineaProductos
                                                .requestMatchers("/lineaProducto/list/**", "/lineaProducto/nuevo/**",
                                                                "/lineaProducto/editar/**", "/lineaProducto/borrar/**")
                                                .hasRole("USER")

                                                // Permisos Compra
                                                .requestMatchers("/compra/list").hasRole("ADMIN")
                                                .requestMatchers("/compra/usuario/**", "/compra/nuevo/**",
                                                                "compra/editar/**", "compra/borrar/**")
                                                .hasRole("USER")

                                                .requestMatchers("/").permitAll()

                                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                                .permitAll()
                                                .requestMatchers("/h2-console/**").permitAll()
                                                .anyRequest().permitAll()

                )
                                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                                                .loginPage("/signin") // mapping para mostrar formulario de login

                                                .loginProcessingUrl("/login") // ruta post de /signin

                                                .failureUrl("/signin?error")
                                                .defaultSuccessUrl("/", true).permitAll())
                                .logout((logout) -> logout
                                                .logoutSuccessUrl("/").permitAll())
                                // .csrf(csrf -> csrf.disable())
                                .httpBasic(Customizer.withDefaults());
                http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/accessError"));
                return http.build();
        }

}