package br.com.wallet.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users_wallet")
public class UserWallet implements Serializable{
	 
	private static final long serialVersionUID = 124054441245587752L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "users", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = "wallet", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Wallet wallet;
	
}
