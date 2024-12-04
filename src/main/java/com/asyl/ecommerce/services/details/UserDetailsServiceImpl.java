package com.asyl.ecommerce.services.details;

import java.util.Arrays;

import com.asyl.ecommerce.domain.users.Client;
import com.asyl.ecommerce.domain.users.Seller;
import com.asyl.ecommerce.repositories.ClientRepository;
import com.asyl.ecommerce.repositories.SellerRepository;
import com.asyl.ecommerce.security.ClientSS;
import com.asyl.ecommerce.security.SellerSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private SellerRepository sellerRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Client cli = clientRepo.findByEmail(email);

		if (cli == null) {
			Seller sel = sellerRepo.findByEmail(email);

			SellerSS selSS = new SellerSS();

			selSS.setId(sel.getId());
			selSS.setEmail(sel.getEmail());
			selSS.setPassword(sel.getPassword());
			selSS.setAuthorities(Arrays.asList(sel.getType()));

			return selSS;
		}

		ClientSS cliSS = new ClientSS();

		cliSS.setId(cli.getId());
		cliSS.setEmail(cli.getEmail());
		cliSS.setPassword(cli.getPassword());
		cliSS.setAuthorities(Arrays.asList(cli.getType()));

		return cliSS;
	}

}
