package br.com.wallet.entities.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	
	private Long id;
	
	@NotNull
	@Length(min=3, max= 50, message="O nome deve conter entre 3 e 50 caracteres")
	private String name;
	
	@NotNull
	@Length(min=6, message="A senha deve conter no mínimo 6 caracteres")
	private String password;
	
	@Email(message = "Email inválido")
	private String email;

}
