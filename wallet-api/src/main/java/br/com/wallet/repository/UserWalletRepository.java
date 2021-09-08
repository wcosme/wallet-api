package br.com.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wallet.entities.UserWallet;

public interface UserWalletRepository extends JpaRepository<UserWallet, Long>{

}
