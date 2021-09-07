package br.com.wallet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.wallet.entities.User;
import br.com.wallet.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<User> findByEmail(String string) {
		
		Optional<User> obj = userRepository.findByEmailEquals(string);
		
		return obj;
	}

}
