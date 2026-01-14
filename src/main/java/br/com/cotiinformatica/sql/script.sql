CREATE TABLE clientes(
	id UUID PRIMARY KEY,
	nome VARCHAR(150) NOT NULL,
	email VARCHAR(100) NOT NULL,
	cpf CHAR(11) NOT NULL UNIQUE,
	telefone VARCHAR(20) NOT NULL,
	data_nascimento DATE NOT NULL,
	data_cadastro TIMESTAMP NOT NULL
);

-- Cadastrando alguns clientes na tabela
INSERT INTO clientes(id, nome, email, cpf, telefone, data_nascimento, data_cadastro)
VALUES
	(gen_random_uuid(), 'Ana Maria', 'anamaria@gmail.com', '12345678900', '2199999-0000', '1980-05-10', CURRENT_TIMESTAMP),
	(gen_random_uuid(), 'João Pedro', 'joaopedro@gmail.com', '32165498700', '2188888-0000', '1990-01-20', CURRENT_TIMESTAMP),
	(gen_random_uuid(), 'Rui Carlos', 'ruicarlos@gmail.com', '98765432100', '2177777-0000', '1985-03-15', CURRENT_TIMESTAMP),
	(gen_random_uuid(), 'Maria Silva', 'mariasilva@gmail.com', '65432198700', '2166666-0000', '1995-09-10', CURRENT_TIMESTAMP);

-- Consultando os clientes na tabela
SELECT id, nome, email, cpf, telefone, data_nascimento, data_cadastro
FROM clientes
ORDER BY nome ASC;