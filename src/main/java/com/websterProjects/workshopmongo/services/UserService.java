package com.websterProjects.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websterProjects.workshopmongo.domain.User;
import com.websterProjects.workshopmongo.dto.UserDTO;
import com.websterProjects.workshopmongo.repository.UserRepository;
import com.websterProjects.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public void delete(String id) {
		if (!repository.existsById(id)) {
			throw new ObjectNotFoundException(id);
		}

		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return repository.save(newUser);
	}

	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}

}
