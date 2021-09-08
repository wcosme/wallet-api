package br.com.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wallet.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmailEquals(String email);

}
