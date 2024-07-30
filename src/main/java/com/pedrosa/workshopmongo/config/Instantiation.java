package com.pedrosa.workshopmongo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pedrosa.workshopmongo.domain.Post;
import com.pedrosa.workshopmongo.domain.User;
import com.pedrosa.workshopmongo.dto.AuthorDTO;
import com.pedrosa.workshopmongo.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(matheus, alex, bob));
		
		Post post1 = new Post(null, LocalDate.parse("2024-07-24"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(matheus));
		Post post2 = new Post(null, LocalDate.parse("2024-08-02"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(matheus));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", LocalDate.parse("2024-07-25"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", LocalDate.parse("2024-07-27"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", LocalDate.parse("2024-08-05"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		matheus.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(matheus);
	}

}
