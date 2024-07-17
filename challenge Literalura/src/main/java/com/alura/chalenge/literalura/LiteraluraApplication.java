package com.alura.chalenge.literalura;

import com.alura.chalenge.literalura.inicio.Inicio;
import com.alura.chalenge.literalura.repo.AutoresRepo;
import com.alura.chalenge.literalura.repo.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepo libroRepo;
	@Autowired
	private AutoresRepo autoresRepo;

	public LiteraluraApplication(LibroRepo libroRepo, AutoresRepo autoresRepo) {
		this.libroRepo = libroRepo;
		this.autoresRepo = autoresRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Inicio inicio = new Inicio(libroRepo, autoresRepo);
		inicio.mostrarMenu();
	}


}
