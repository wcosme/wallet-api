package br.com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.wallet.entities.User;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class UserRepositoryTes {
	
	private static final String EMAIL = "teste@teste.com.br";
	
	@Autowired
	UserRepository userRepository;
	
	@BeforeEach
	public void setUp() {
		User u = new User();
		u.setName("Set up User");
		u.setPassword("Senha123");
		u.setEmail(EMAIL);
		
		userRepository.save(u);
	}
	
	@AfterEach
	public void tearDown() {
		userRepository.deleteAll();
	}
	
	@Test
	public void testSave() {
		
		User u = new User();
		u.setName("Teste");
		u.setPassword("123");
		u.setEmail("teste@teste.com.br");
		
		User response = userRepository.save(u);
		
		assertNotNull(response);
		
	}
	
	public void testFindByEmail() {
		Optional<User> response = userRepository.findByEmailEquals(EMAIL);
		
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}
}
