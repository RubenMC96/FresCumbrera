package com.rmc.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rmc.app.domain.Categoria;
import com.rmc.app.domain.Producto;
import com.rmc.app.service.CategoriaService;
import com.rmc.app.service.ProductoService;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(CategoriaService categoriaService, ProductoService productoService) {

		return args -> {
			categoriaService.añadir(new Categoria(0L, "Frutas"));
			categoriaService.añadir(new Categoria(0L, "Preparados"));
			productoService.añadir(new Producto(1L,"Fresas",true,3D,"Huelva",categoriaService.obtenerPorNombre("Fruta")));
			productoService.añadir(new Producto(1L,"Batidos",true,10D,"España",categoriaService.obtenerPorNombre("Preparados")));
		};
	}

	@Bean
		public BCryptPasswordEncoder passwordEncoder() {
   		return new BCryptPasswordEncoder();
	}


}
