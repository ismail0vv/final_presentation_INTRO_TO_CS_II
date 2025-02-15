package com.asyl.ecommerce.resources;

import java.util.List;

import com.asyl.ecommerce.domain.dto.ranking.ClientRankingDTO;
import com.asyl.ecommerce.domain.dto.updated.UpdatedClient;
import com.asyl.ecommerce.domain.users.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asyl.ecommerce.services.ClientService;
import com.asyl.ecommerce.services.RankingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping
@Api(value = "Client resource")
@CrossOrigin(origins = "*")
public class ClientResource {

	@Autowired
	private ClientService service;
	
	@Autowired
	private RankingService ranking;

	@GetMapping("/clients")
	@ApiOperation(value = "Return all clients")
	public ResponseEntity<List<Client>> findAll() {

		return ResponseEntity.ok().body(service.findAll());
	}

	@ApiOperation(value = "Return a client by id")
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> findById(@PathVariable Integer id) {

		Client obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@ApiOperation(value = "Create a client")
	@PostMapping("/create/client")
	public ResponseEntity<Client> insert(@RequestBody Client obj) {

		service.insert(obj);

		return ResponseEntity.ok().body(obj);
	}

	@PutMapping("/update/client")
	@ApiOperation(value = "Update a client ")
	public ResponseEntity<Client> update(@RequestBody UpdatedClient obj){

		Client cli =  service.update(obj);
		return ResponseEntity.ok().body(cli);
	}

	@DeleteMapping("/delete/client")
	@ApiOperation(value = "Delete a client")
	public ResponseEntity<Void> delete() {
		service.delete();

		return ResponseEntity.noContent().build();
	}
	

	@ApiOperation(value = "Return a list of clients who buys the most")
	@GetMapping("/clients/ranking")
	public ResponseEntity<List<ClientRankingDTO>> returnRankingClient() {
		
		
		return ResponseEntity.ok().body(ranking.returnRankingClient());
	}
}
