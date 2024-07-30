package com.pedrosa.workshopmongo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrosa.workshopmongo.domain.Post;
import com.pedrosa.workshopmongo.repository.PostRepository;
import com.pedrosa.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate) {
		return repo.fullSearch(text, minDate, maxDate);
	}
}
