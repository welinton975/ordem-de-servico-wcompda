drop database ordem_servico;
create database ordem_servico;
use ordem_servico;

CREATE TABLE usuario (
  idUsuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  senha VARCHAR(45) NULL,
  data_cadastro DATE NULL,
  PRIMARY KEY(idUsuario)
);

CREATE TABLE tecnico (
  idTecnico INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  cpf VARCHAR(20) NULL,
  rg VARCHAR(20) NULL,
  PRIMARY KEY(idTecnico),
  FOREIGN KEY(idTecnico) REFERENCES usuario(idUsuario)
);

CREATE TABLE cliente (
  idCliente INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  contato VARCHAR(255) NULL,
  cnpj VARCHAR(45) NULL,
  insc_estadual VARCHAR(45) NULL,
  endereco VARCHAR(255) NULL,
  complemento VARCHAR(45) NULL,
  bairro VARCHAR(45) NULL,
  cidade VARCHAR(45) NULL,
  estado VARCHAR(45) NULL,
  cep VARCHAR(20) NULL,
  telefone VARCHAR(20) NULL,
  fax VARCHAR(20) NULL,
  observacao TEXT NULL,
  PRIMARY KEY(idCliente),
  FOREIGN KEY(idCliente) REFERENCES usuario(idUsuario)
);

CREATE TABLE ordem_servico (
  idOrdemServico INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  idCliente INTEGER UNSIGNED NOT NULL,
  estado VARCHAR(45) NULL,
  orcamento FLOAT NULL,
  PRIMARY KEY(idOrdemServico),
  FOREIGN KEY(idCliente) REFERENCES cliente(idCliente)
);

CREATE TABLE aparelho_cliente (
  idAparelhoCliente INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  idCliente INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(45) NULL,
  descricao_defeito VARCHAR(255) NULL,
  estado VARCHAR(20) NULL,
  cor VARCHAR(20) NULL,
  modelo VARCHAR(45) NULL,
  PRIMARY KEY(idAparelhoCliente),
  FOREIGN KEY(idCliente) REFERENCES cliente(idCliente)
);

CREATE TABLE log (
  idLog INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  data DATE NULL,
  descricao VARCHAR(255) NULL,
  PRIMARY KEY(idLog)
);

CREATE TABLE item_log (
  idUsuario INTEGER UNSIGNED NOT NULL,
  idLog INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idUsuario, idLog),
  FOREIGN KEY(idUsuario) REFERENCES usuario(idUsuario),
  FOREIGN KEY(idLog) REFERENCES log(idLog)
);

CREATE TABLE servico (
  idServico INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  idOrdemServico INTEGER UNSIGNED NOT NULL,
  idAparelhoCliente INTEGER UNSIGNED NOT NULL,
  idTecnico INTEGER UNSIGNED NOT NULL,
  descricao VARCHAR(255) NULL,
  preco FLOAT NULL,
  tempo_reparo VARCHAR(45) NULL,
  PRIMARY KEY(idServico),
  FOREIGN KEY(idOrdemServico) REFERENCES ordem_servico(idOrdemServico),
  FOREIGN KEY(idAparelhoCliente) REFERENCES aparelho_cliente(idAparelhoCliente),
  FOREIGN KEY(idTecnico) REFERENCES tecnico(idTecnico)
);

CREATE TABLE peca (
  idPeca INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  quantidade INT NULL,
  preco FLOAT NULL,
  PRIMARY KEY(idPeca)
);

CREATE TABLE ItemServico (
  idServico INTEGER UNSIGNED NOT NULL,
  idPeca INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idServico, idPeca),
  FOREIGN KEY(idServico) REFERENCES servico(idServico),
  FOREIGN KEY(idPeca) REFERENCES peca(idPeca)
);

CREATE TABLE Fornecedor (
  idFornecedor INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  contato VARCHAR(45) NULL,
  nome VARCHAR(45) NULL,
  localizacao VARCHAR(45) NULL,
  PRIMARY KEY(idFornecedor)
);

CREATE TABLE ItemFornecimento (
  idPeca INTEGER UNSIGNED NOT NULL,
  idFornecedor INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idPeca, idFornecedor),
  FOREIGN KEY(idPeca) REFERENCES peca(idPeca),
  FOREIGN KEY(idFornecedor) REFERENCES fornecedor(idFornecedor)
);














