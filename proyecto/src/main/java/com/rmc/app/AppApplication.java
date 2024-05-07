package com.rmc.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rmc.app.domain.Rol;
import com.rmc.app.domain.Usuario;
import com.rmc.app.service.UsuarioService;


@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
     

	// @Bean
	// CommandLineRunner initData(UsuarioService usuarioService) {
	// 	return args -> {

	// 		usuarioService.añadir(
	// 			new Usuario(0L, 
	// 					"administrador", 
	// 					"1",
	// 					"admin1@gmail.com", 
	// 					"admin1", 
	// 					"1234",
	// 					"123456789",
	// 					Rol.ADMIN));

	// 		usuarioService.añadir(
	// 			new Usuario(0L, 
	// 					"administrador", 
	// 					"2",
	// 					"admin2@gmail.com", 
	// 					"admin2", 
	// 					"1234",
	// 					"134679258",
	// 					Rol.ADMIN));
				
	// 		usuarioService.añadir(
	// 			new Usuario(0L, 
	// 					"usuario", 
	// 					"1",
	// 					"usuario@gmail.com", 
	// 					"user1", 
	// 					"1234",
	// 					"987654321",
	// 					Rol.USER));
				
			
	// 	};
	// }

}
