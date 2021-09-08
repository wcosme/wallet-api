package br.com.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wallet.entities.UserWallet;
import br.com.wallet.repository.UserWalletRepository;
import br.com.wallet.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService {
	
	@Autowired
	private UserWalletRepository userWalletRepository;

	@Override
	public UserWallet save(UserWallet userWallet) {
		return userWalletRepository.save(userWallet);
	}

}
