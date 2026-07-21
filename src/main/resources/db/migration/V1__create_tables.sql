CREATE TABLE tb_clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(255) UNIQUE,
    numerocnh VARCHAR(255) UNIQUE
);

CREATE TABLE tb_usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE tb_veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    ano INT,
    placa VARCHAR(255),
    cor VARCHAR(255),
    valor_diaria DECIMAL(10,2),
    disponivel BOOLEAN
);

CREATE TABLE tb_locacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_retirada DATE,
    data_prevista_devolucao DATE,
    data_devolucao DATE,
    valor_total DECIMAL(10,2),
    status VARCHAR(50),
    cliente_id BIGINT,
    veiculo_id BIGINT,
    CONSTRAINT fk_locacao_cliente FOREIGN KEY (cliente_id) REFERENCES tb_clientes(id),
    CONSTRAINT fk_locacao_veiculo FOREIGN KEY (veiculo_id) REFERENCES tb_veiculos(id)
);