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
		SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
		sfd.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		
		Post post1 = new Post(null, sfd.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo", maria);
		Post post2 = new Post(null, sfd.parse("23/03/2020"), "Bom dia!", "Acordei bem", alex);
		
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1,post2));
	}

}
