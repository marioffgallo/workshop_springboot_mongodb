package com.marioffgallo.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.marioffgallo.workshopmongo.domain.User;
import com.marioffgallo.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User mj = new User(null, "Michael Jackson", "mj@gmail.com");
		User bobm = new User(null, "Bob Marley", "bobm@hotmail.com");
		User bruce = new User(null, "Bruce Wayne", "batman@uol.com.br");
		
		userRepository.saveAll(Arrays.asList(mj, bobm, bruce));
	}

}
