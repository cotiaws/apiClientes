package br.com.cotiinformatica.dtos;

import java.util.Date;
import java.util.UUID;

public record ClienteResponseDto(
		UUID id,				//Id do cliente
		String nome,			//Nome do cliente
		String email,			//Email
		String cpf,				//Cpf
		String telefone,		//Telefone
		Date dataNascimento		//Data de nascimento		
		) {		
}
