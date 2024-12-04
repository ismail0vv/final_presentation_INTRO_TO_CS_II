package com.asyl.ecommerce.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.asyl.ecommerce.domain.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

	@Transactional
	Seller findByEmail(String email);

	@Transactional
	Seller findByCpf(String cpf);

	@Modifying
	@Query(value = "select * from tb_sellers order by how_much_money_this_seller_has_sold DESC limit 10 ", nativeQuery = true)
	List<Seller> returnRankingSeller();
}
