package com.rmc.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rmc.app.domain.Categoria;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Rol;
import com.rmc.app.domain.Usuario;
import com.rmc.app.service.CategoriaService;
import com.rmc.app.service.ProductoService;
import com.rmc.app.service.UsuarioService;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
 
	// @Bean
	// public CommandLineRunner initData(CategoriaService categoriaService, ProductoService productoService,
	// 		UsuarioService usuarioService) {

	// 	return args -> {
	// 		usuarioService.añadir(new Usuario(0L, "admin", "1", "admin1@gmail.com", "admin1", "1234", "123456789", Rol.ADMIN));
	// 		usuarioService.añadir(new Usuario(0L, "user", "1", "user1@gmail.com", "user1", "1234", "123456789", Rol.USER));
	// 		categoriaService.añadir(new Categoria(0L, "Frutas"));
	// 		categoriaService.añadir(new Categoria(0L, "Preparados"));
	// 		productoService
	// 				.añadir(new Producto(1L, "Fresas", true, 3D, "Huelva",10, categoriaService.obtenerPorNombre("Fruta")));
	// 		productoService.añadir(
	// 				new Producto(1L, "Batidos", true, 10D, "España",5,categoriaService.obtenerPorNombre("Preparados")));
	// 	};
	// }

}
