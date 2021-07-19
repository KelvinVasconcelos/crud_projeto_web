package com.games.projeto_web.games_projeto_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class GamesProjetoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesProjetoWebApplication.class, args);
	}

}
