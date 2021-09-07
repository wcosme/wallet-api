package br.com.wallet.service;

import java.util.Optional;

import br.com.wallet.entities.User;

public interface UserService {

	Optional<User> findByEmail(String string);	

}
