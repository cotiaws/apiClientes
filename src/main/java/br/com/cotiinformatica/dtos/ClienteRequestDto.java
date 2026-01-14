package br.com.cotiinformatica.dtos;

public record ClienteRequestDto(
		String nome,			//Nome do cliente
		String email,			//Email
		String cpf,				//Cpf
		String telefone,		//Telefone
		String dataNascimento	//Data de nascimento
		) {

}
