package br.com.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wallet.service.UserWalletService;

@RestController
@RequestMapping(name = "user-wallet")
public class UserWalletController {

	@Autowired
	private UserWalletService userWalletService;
}
