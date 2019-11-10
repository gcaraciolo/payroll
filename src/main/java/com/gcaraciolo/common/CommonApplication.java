package com.gcaraciolo.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Criado apenas para que os testes possam rodar. Sem essa classe o spring não
 * consegue entrar o contexto dos testes das classes dentro de common.
 */
@SpringBootApplication
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

}
