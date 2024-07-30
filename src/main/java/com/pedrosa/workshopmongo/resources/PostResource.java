package com.pedrosa.workshopmongo.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrosa.workshopmongo.domain.Post;
import com.pedrosa.workshopmongo.resources.util.URL;
import com.pedrosa.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTtile(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<Post>> fullsearch(
			@RequestParam(value="text") String text, 
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		LocalDate min = URL.convertData(minDate, LocalDate.ofEpochDay(0L));
		LocalDate max = URL.convertData(maxDate, LocalDate.now());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
