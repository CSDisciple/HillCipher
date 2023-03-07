package com.csdisciple.hill_cipher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class HillCipherApplication {

	public static void main(String[] args) {

		SpringApplication.run(HillCipherApplication.class, args);
		Logger logger = Logger.getLogger(HillCipherApplication.class.getName());
		logger.severe("Hello world");


	}

}
