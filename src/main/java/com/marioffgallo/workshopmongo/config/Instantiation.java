package com.marioffgallo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.marioffgallo.workshopmongo.domain.Post;
import com.marioffgallo.workshopmongo.domain.User;
import com.marioffgallo.workshopmongo.dto.AuthorDTO;
import com.marioffgallo.workshopmongo.repository.PostRepository;
import com.marioffgallo.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User mj = new User(null, "Michael Jackson", "mj@gmail.com");
		User bobm = new User(null, "Bob Marley", "bobm@hotmail.com");
		User bruce = new User(null, "Bruce Wayne", "batman@uol.com.br");
		
		userRepository.saveAll(Arrays.asList(mj, bobm, bruce));
		
		Post post1 = new Post(null, sdf.parse("21/03/2010"), "Partiu viagem!", "Vou viajar para São Paulo, abraços!", new AuthorDTO(mj));
		Post post2 = new Post(null, sdf.parse("31/03/2010"), "Bom dia", "Acordei com dor de barriga", new AuthorDTO(mj));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		mj.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(mj);		
	}

}
