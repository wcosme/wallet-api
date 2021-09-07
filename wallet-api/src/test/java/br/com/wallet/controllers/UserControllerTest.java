package br.com.wallet.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wallet.entities.User;
import br.com.wallet.entities.dto.UserDTO;
import br.com.wallet.service.UserService;

@AutoConfigureMockMvc
@SpringBootTest
@Profile(value = "test")
public class UserControllerTest {
	
	private static final String EMAIL = "Teste@gmail.com";
	private static final String NAME = "WC";
	private static final String PASSWORD = "123";
	private static final String URL = "/users";

	@MockBean
	UserService userService;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception{
		
		BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	public User getMockUser() {
		User user = new User();
		user.setEmail(EMAIL);
		user.setName(NAME);
		user.setPassword(PASSWORD);
		
		return user;
	}
	
	public String getJsonPayload() throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setEmail(EMAIL);
		dto.setName(NAME);
		dto.setPassword(PASSWORD);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

}
