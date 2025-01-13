package com.websterProjects.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.websterProjects.workshopmongo.domain.Post;
import com.websterProjects.workshopmongo.domain.User;
import com.websterProjects.workshopmongo.dto.AuthorDTO;
import com.websterProjects.workshopmongo.dto.CommentDTO;
import com.websterProjects.workshopmongo.repository.PostRepository;
import com.websterProjects.workshopmongo.repository.UserRepository;


@Configuration 
@Profile("test")
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
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2020"), "Bom dia!", "Acordei bem", new AuthorDTO(alex));
		
		CommentDTO c1 = new CommentDTO("Boa viagem", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c2 = new CommentDTO("Aproveita!", sdf.parse("22/03/2018"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("22/03/2018"), new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().add(post1);
		alex.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(maria, alex));
	}

}
