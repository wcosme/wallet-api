package br.com.wallet.service;

import java.util.Optional;

import br.com.wallet.entities.User;

public interface UserService {
	
	User save(User user);

	Optional<User> findByEmail(String email);	

}
