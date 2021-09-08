package br.com.wallet.entities.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserWalletDTO {
	
	private Long id;
	
	@NotNull(message = "Informe o ID do Usuário")
	private Long user;
	
	@NotNull(message = "Informe o ID do Carteira")
	private Long wallet;

}
