package br.com.wallet.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wallet.entities.Wallet;
import br.com.wallet.entities.dto.WalletDTO;
import br.com.wallet.response.Response;
import br.com.wallet.service.WalletService;

@RestController
@RequestMapping("wallets")
public class WalletController {
	
	@Autowired
	private WalletService service;

	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO dto, BindingResult result) {
		Response<WalletDTO> response = new Response<WalletDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Wallet w = service.save(this.convertDtoToEntity(dto));

		response.setData(this.convertEntityToDto(w));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/V2")
	public ResponseEntity<Wallet> createV2(@Valid @RequestBody WalletDTO dto) {			
		Wallet obj = service.save(this.convertDtoToEntity(dto));		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	private Wallet convertDtoToEntity(WalletDTO dto) {
		Wallet w = new Wallet();
		w.setId(dto.getId());
		w.setName(dto.getName());
		w.setValue(dto.getValue());
		
		return w;
	}
	
	private WalletDTO convertEntityToDto(Wallet w) {
		WalletDTO dto = new WalletDTO();
		dto.setId(w.getId());
		dto.setName(w.getName());
		dto.setValue(w.getValue());

		return dto;
	}
}
