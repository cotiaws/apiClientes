package br.com.cotiinformatica.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {

	@Autowired
	private ConnectionFactory connectionFactory;

	// Método para inserir um cliente no banco de dados
	public void inserir(Cliente cliente) throws Exception {

		// Abrindo conexão com o banco de dados
		var connection = connectionFactory.getConnection();

		// Escrevendo o script SQL que será executado no banco de dados
		var query = """
					INSERT INTO clientes(id, nome, email, cpf, telefone, data_nascimento, data_cadastro)
					VALUES(?,?,?,?,?,?,?)
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(query);
		statement.setObject(1, cliente.getId());
		statement.setString(2, cliente.getNome());
		statement.setString(3, cliente.getEmail());
		statement.setString(4, cliente.getCpf());
		statement.setString(5, cliente.getTelefone());
		statement.setDate(6, new java.sql.Date(cliente.getDataNascimento().getTime()));
		statement.setDate(7, new java.sql.Date(cliente.getDataCadastro().getTime()));
		statement.execute();

		// Fechando a conexão do banco
		connection.close();
	}

	// Método para atualizar um cliente no banco de dados
	public boolean atualizar(Cliente cliente) throws Exception {

		// Abrindo conexão com o banco de dados
		var connection = connectionFactory.getConnection();

		// Escrevendo o script SQL que será executado no banco de dados
		var query = """
					UPDATE clientes
					SET
						nome = ?,
						email = ?,
						cpf = ?,
						telefone = ?,
						data_nascimento = ?
					WHERE
						id = ?
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(query);
		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getCpf());
		statement.setString(4, cliente.getTelefone());
		statement.setDate(5, new java.sql.Date(cliente.getDataNascimento().getTime()));
		statement.setObject(6, cliente.getId());
		var rowsAffected = statement.executeUpdate();

		// Fechando a conexão do banco
		connection.close();

		// retornando true ou false
		return rowsAffected > 0;
	}

	// Método para excluir um cliente no banco de dados
	public boolean excluir(UUID id) throws Exception {

		// Abrindo conexão com o banco de dados
		var connection = connectionFactory.getConnection();

		// Escrevendo o script SQL que será executado no banco de dados
		var query = """
					DELETE FROM clientes
					WHERE id = ?
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(query);
		statement.setObject(1, id);
		var rowsAffected = statement.executeUpdate();

		// fechando a conexão do banco de dados
		connection.close();

		// retornando true ou false
		return rowsAffected > 0;
	}

	// Método para retornar uma lista de clientes do banco de dados
	public List<Cliente> consultar(String nome) throws Exception {

		// Abrindo conexão com o banco de dados
		var connection = connectionFactory.getConnection();

		// Escrevendo o script SQL que será executado no banco de dados
		var query = """
					SELECT id, nome, email, cpf, telefone, data_nascimento, data_cadastro
					FROM clientes
					WHERE nome ILIKE ?
					ORDER BY nome ASC
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(query);
		statement.setString(1, "%" + nome + "%");
		var result = statement.executeQuery();

		// Criando uma lista de clientes vazia
		var clientes = new ArrayList<Cliente>();

		// percorrendo cada cliente obtido na consulta
		while (result.next()) {

			// criando um objeto para ler os dados do cliente
			var cliente = new Cliente();

			// ler cada coluna da tabela
			cliente.setId(UUID.fromString(result.getString("id")));
			cliente.setNome(result.getString("nome"));
			cliente.setEmail(result.getString("email"));
			cliente.setCpf(result.getString("cpf"));
			cliente.setTelefone(result.getString("telefone"));
			cliente.setDataNascimento(new Date(result.getDate("data_nascimento").getTime()));
			cliente.setDataCadastro(new Date(result.getDate("data_cadastro").getTime()));

			// adicionando cada cliente dentro da lista
			clientes.add(cliente);
		}

		// fechar a conexão do banco de dados
		connection.close();

		// retornar a lista
		return clientes;
	}

	// Método para retornar 1 cliente do banco de dados
	public Cliente ObterPorId(UUID id) throws Exception {

		// Abrindo conexão com o banco de dados
		var connection = connectionFactory.getConnection();

		// Escrevendo o script SQL que será executado no banco de dados
		var query = """
					SELECT id, nome, email, cpf, telefone, data_nascimento, data_cadastro
					FROM clientes
					WHERE id = ?
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(query);
		statement.setObject(1, id);
		var result = statement.executeQuery();

		// Criando um objeto Cliente com valor null (vazio)
		Cliente cliente = null;

		// percorrendo cada cliente obtido na consulta
		if (result.next()) {

			// criando um objeto para ler os dados do cliente
			cliente = new Cliente();

			// ler cada coluna da tabela
			cliente.setId(UUID.fromString(result.getString("id")));
			cliente.setNome(result.getString("nome"));
			cliente.setEmail(result.getString("email"));
			cliente.setCpf(result.getString("cpf"));
			cliente.setTelefone(result.getString("telefone"));
			cliente.setDataNascimento(new Date(result.getDate("data_nascimento").getTime()));
			cliente.setDataCadastro(new Date(result.getDate("data_cadastro").getTime()));
		}

		// fechar a conexão do banco de dados
		connection.close();

		// retornar a lista
		return cliente;
	}
}
