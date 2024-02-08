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

                        
                        .requestMatchers("/categoria/nuevo/**", "/categoria/editar/**", "/categoria/borrar/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/categoria/").permitAll()

                        .requestMatchers("/producto/nuevo/**", "/producto/editar/**", "/producto/borrar/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/producto/list/**").permitAll()


                        .requestMatchers("/usuario/", "/usuario/editar/**", "/usuario/borrar/**").hasAnyRole("ADMIN", "MANAGER")

                        .requestMatchers("/categoria/").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/categoria/nuevo/**", "/categoria/editar/**", "/categoria/borrar/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/valoracion/nuevo/**").hasAnyRole("MANAGER", "ADMIN", "USER")
                        .requestMatchers("/valoracion/usuario/**", "/valoracion/editar/**", "/valoracion/borrar/**")
                        .hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers("/lineaProducto/nuevo/**").hasAnyRole("MANAGER", "ADMIN", "USER")
                        .requestMatchers("/lineaProducto/usuario/**", "/lineaProducto/editar/**", "/lineaProducto/borrar/**")
                        .hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers("/compra/nuevo/**").hasAnyRole("MANAGER", "ADMIN", "USER")
                        .requestMatchers("/compra/usuario/**", "/compra/editar/**", "/compra/borrar/**")
                        .hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers("/usuario/nuevo/**").hasAnyRole("MANAGER", "ADMIN", "USER")
                        .requestMatchers("/usuario/usuario/**", "/usuario/editar/**", "/usuario/borrar/**")
                        .hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers("/", "/public/**", "/categoria/", "/producto/",
                                "/valoracion/producto/**")
                        .permitAll()

                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/h2-console/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/signin") // mapping par mostrar formulario de login

                        .loginProcessingUrl("/login") // ruta post de /signin

                        .failureUrl("/signin")
                        .defaultSuccessUrl("/home", true).permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/home").permitAll())
                // .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());
        http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/accessError"));
        return http.build();
    }
}