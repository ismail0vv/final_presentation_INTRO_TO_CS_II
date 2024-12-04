package com.asyl.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.asyl.ecommerce.domain.dto.ranking.ClientRankingDTO;
import com.asyl.ecommerce.domain.dto.ranking.SellerRankingDTO;
import com.asyl.ecommerce.domain.users.Client;
import com.asyl.ecommerce.domain.users.Seller;
import com.asyl.ecommerce.repositories.ClientRepository;
import com.asyl.ecommerce.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private SellerRepository sellerRepo;

	public List<ClientRankingDTO> returnRankingClient() {

		List<Client> clients = clientRepo.returnRankingClient();
		List<ClientRankingDTO> rankingDTO = new ArrayList<>();

		clients.stream().forEach(x -> rankingDTO.add(new ClientRankingDTO(x.getId(), x.getName(), x.getNumberOfBuys(),
				x.getHowMuchMoneyThisClientHasSpent())));

		return rankingDTO;
	}

	public List<SellerRankingDTO> returnRankingSeller() {

		List<Seller> sellers = sellerRepo.returnRankingSeller();
		List<SellerRankingDTO> rankingDTO = new ArrayList<>();

		sellers.stream().forEach(x -> rankingDTO.add(new SellerRankingDTO(x.getId(), x.getName(), x.getNumberOfSells(),
				x.getHowMuchMoneyThisSellerHasSold())));

		return rankingDTO;
	}

}
