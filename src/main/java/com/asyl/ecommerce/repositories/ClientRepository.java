package com.asyl.ecommerce.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.asyl.ecommerce.domain.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Transactional
	Client findByEmail(String email);
	
	@Transactional
	Client findByCpf(String cpf);
	
	
	@Modifying
	@Query(value="select * from tb_clients order by how_Much_Money_This_Client_Has_Spent DESC limit 10 ",nativeQuery = true)
	List<Client> returnRankingClient();
}
