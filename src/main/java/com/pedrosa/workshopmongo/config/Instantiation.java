package com.pedrosa.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pedrosa.workshopmongo.domain.Post;
import com.pedrosa.workshopmongo.domain.User;
import com.pedrosa.workshopmongo.repository.PostRepository;
import com.pedrosa.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User matheus = new User(null, "Matheus Pedrosa", "matheus@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, Instant.parse("2024-07-24T12:53:07Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", matheus);
		Post post2 = new Post(null, Instant.parse("2024-08-02T12:53:07Z"), "Bom dia", "Acordei feliz hoje!", matheus);
		
		userRepository.saveAll(Arrays.asList(matheus, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
