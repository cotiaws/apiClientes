package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ClienteRequestDto;
import br.com.cotiinformatica.dtos.ClienteResponseDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	/*
	 * Método para realizar o cadastro de um cliente
	 */
	@PostMapping
	public String post(@RequestBody ClienteRequestDto request) throws Exception {
		
		//Criando um objeto da classe de entidade
		var cliente = new Cliente();
		
		//Copiando as informações do DTO para a entidade
		cliente.setId(UUID.randomUUID()); //gerando um ID
		cliente.setNome(request.nome());
		cliente.setEmail(request.email());
		cliente.setCpf(request.cpf());
		cliente.setTelefone(request.telefone());
		cliente.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(request.dataNascimento()));
		cliente.setDataCadastro(new Date()); //preenchendo com a data atual

		clienteRepository.inserir(cliente);
		
		return "Cliente " + cliente.getNome() + ", cadastrado com sucesso!";
	}		
	
	/*
	 * Método para realizar a atualização dos dados de um cliente
	 */
	@PutMapping("{id}")
	public String put(@PathVariable UUID id, @RequestBody ClienteRequestDto request) throws Exception {
		
		//Criando um objeto da classe de entidade
		var cliente = new Cliente();
				
		//Copiando as informações do DTO para a entidade
		cliente.setId(id);
		cliente.setNome(request.nome());
		cliente.setEmail(request.email());
		cliente.setCpf(request.cpf());
		cliente.setTelefone(request.telefone());
		cliente.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(request.dataNascimento()));

		clienteRepository.atualizar(cliente);
		
		return "Cliente " + cliente.getNome() + ", atualizado com sucesso.";
	}		
	
	/*
	 * Método para realizar a exclusão dos dados de um cliente
	 */
	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) throws Exception {

		clienteRepository.excluir(id);
		
		return "Cliente excluído com sucesso.";
	}	

	/*
	 * Método para realizar a consulta de clientes
	 */
	@GetMapping
	public List<ClienteResponseDto> getAll(@RequestParam String nome) throws Exception {

		//consultar os clientes no banco de dados
		var clientes = clienteRepository.consultar(nome);
		
		//copiar os dados da lista de clientes para uma lista do dto
		return clientes.stream()
				.map(c -> new ClienteResponseDto( //preenchendo o DTO
							c.getId(), //id do cliente
							c.getNome(), //nome do cliente
							c.getEmail(), //email do cliente
							c.getCpf(), //cpf do cliente
							c.getTelefone(), //telefone do cliente
							c.getDataNascimento() //data de nascimento
						))
				.collect(Collectors.toList()); //lista de ClienteResponseDto
	}
	
	/*
	 * Método para realizar a consulta de clientes
	 */
	@GetMapping("{id}")
	public ClienteResponseDto getById(@PathVariable UUID id) throws Exception {

		//consultar o cliente no banco de dados
		var cliente = clienteRepository.ObterPorId(id);
		
		//copiar os dados da lista de clientes para uma lista do dto
		return new ClienteResponseDto( //preenchendo o DTO
				cliente.getId(), //id do cliente
				cliente.getNome(), //nome do cliente
				cliente.getEmail(), //email do cliente
				cliente.getCpf(), //cpf do cliente
				cliente.getTelefone(), //telefone do cliente
				cliente.getDataNascimento()); //data de nascimento
	}
	
}
