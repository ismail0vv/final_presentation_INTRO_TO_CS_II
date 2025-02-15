package com.asyl.ecommerce.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.asyl.ecommerce.domain.dto.updated.UpdatedClient;
import com.asyl.ecommerce.domain.users.Client;
import com.asyl.ecommerce.repositories.ClientRepository;
import com.asyl.ecommerce.repositories.SellerRepository;
import com.asyl.ecommerce.security.ClientSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.asyl.ecommerce.exceptions.AuthorizationException;
import com.asyl.ecommerce.exceptions.ClientOrSellerHasThisSameEntryException;
import com.asyl.ecommerce.exceptions.DuplicateEntryException;
import com.asyl.ecommerce.exceptions.ObjectNotFoundException;
import com.asyl.ecommerce.exceptions.UserHasProductsRelationshipsException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private SellerRepository sellerRepo;



	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	


	public Client findById(Integer id) {

		ClientSS user = UserService.clientAuthenticated();

		if (user == null || !user.getId().equals(id)) {
			throw new AuthorizationException();
		}
		Optional<Client> obj = clientRepo.findById(id);

		try {
			return obj.get();
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException();
		}

	}

	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj.setPassword(passwordEncoder.encode(obj.getPassword()));
		

		if (sellerRepo.findByEmail(obj.getEmail()) == null) {
			try {
				return clientRepo.save(obj);
			} catch (Exception e) {
				throw new DuplicateEntryException();
			}
		}

		throw new ClientOrSellerHasThisSameEntryException("Seller");

	}

	@Transactional
	public Client update(UpdatedClient obj) {
		ClientSS user = UserService.clientAuthenticated();
		
		Client cli = findById(user.getId());

		if (user == null || !user.getId().equals(cli.getId())) {
			throw new AuthorizationException();
		}
		
		cli.setCpf(obj.getCpf());
		cli.setEmail(obj.getEmail());
		cli.setName(obj.getName());
		cli.setPassword(passwordEncoder.encode(obj.getPassword()));


		
		if (sellerRepo.findByEmail(cli.getEmail()) == null) {
			try {
				return clientRepo.save(cli);
			} catch (Exception e) {
				throw new DuplicateEntryException();
			}
		}

		throw new ClientOrSellerHasThisSameEntryException("seller");

	}

	public void delete() {
		ClientSS user = UserService.clientAuthenticated();
		
		Client cli = findById(user.getId());
		
		// verify if the client hasn't bought any products
		// doing this by numberOfBuys because the performance
		if(cli.getNumberOfBuys() == 0) {
			clientRepo.deleteById(user.getId());
		}
		
		else {
			throw new UserHasProductsRelationshipsException();

		}
		
	}
	


}
