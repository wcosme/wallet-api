package br.com.wallet.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
	
	private static final Long ID = 1L;
	private static final String EMAIL = "email@teste.com";
	private static final String NAME = "User Test";
	private static final String PASSWORD = "123456";
	private static final String URL = "/users";

	@MockBean
	UserService userService;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception{
		
		BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, EMAIL, NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.email").value(EMAIL))
				.andExpect(jsonPath("$.data.name").value(NAME))
				//.andExpect(jsonPath("$.data.role").value(RoleEnum.ROLE_ADMIN.toString()))
				.andExpect(jsonPath("$.data.password").doesNotExist());
	}
	
	@Test
	public void testSaveInvalidUser() throws JsonProcessingException, Exception {
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "email", NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value("Email inválido"));
		
	}
	
	public User getMockUser() {
		User u = new User();
		u.setId(ID);
		u.setEmail(EMAIL);
		u.setName(NAME);
		u.setPassword(PASSWORD);
		//u.setRole(RoleEnum.ROLE_ADMIN);
		
		return u;
	}
	
	public String getJsonPayload(Long id, String email, String name, String password) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setName(name);
		dto.setPassword(password);
		//dto.setRole(RoleEnum.ROLE_ADMIN.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

}
