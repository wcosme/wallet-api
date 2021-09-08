package br.com.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wallet.entities.User;
import br.com.wallet.repository.UserRepository;
import br.com.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<User> findByEmail(String email) {		
		return userRepository.findByEmailEquals(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);		
	}

}
