package com.rmc.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rmc.app.domain.Categoria;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.TipoIva;
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
			categoriaService.añadir(new Categoria(0L, "Ordenadores"));
			categoriaService.añadir(new Categoria(0L, "Bombillas"));
			productoService.añadir(new Producto(1L,"HP invictus",true,TipoIva.NORMAL,1000D,categoriaService.obtenerPorNombre("Ordenadores")));
		};
	}

}
