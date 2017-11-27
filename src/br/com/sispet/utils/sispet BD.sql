create database sispet;

use sispet;

create table usuario(
	id bigint primary key AUTO_INCREMENT,
	nome varchar(100) not null,
	usuario varchar(20) not null,
	senha varchar(150) not null,
	perfil char(3) not null, /*VET - veterinario, ADM - Administrador*/
	data_cadastro timestamp 
);

create table veterinario(
	id_usuario bigint,
	cpf varchar(14) primary key,
	email varchar(80) not null,
	especialidade varchar(20),
	telefone varchar(15),

	foreign key(id_usuario) references usuario(id)
);

create table cliente(
	id bigint primary key AUTO_INCREMENT,
	id_veterinario bigint not null,
	nome varchar(80) not null,
	cpf varchar(14) not null,
	email varchar(80) not null,
	sexo enum('M','F') not null,
	telefone varchar(15),
	endereco varchar(50) not null,
	numero int not null ,
	complemento varchar(20),

	foreign key(id_veterinario) references veterinario(id)
);

create table animal(
	id bigint primary key AUTO_INCREMENT,
	id_cliente bigint not null,
	nome varchar(80) not null,
	sexo enum('M','F') not null,
	especie varchar(50) not null,
	raca varchar(50) not null,
	idade int not null,
	peso int not null,
	observacoes text,
	foto varchar(100),

	foreign key (id_cliente) references cliente(id)
);


create table rc_vet_animal(
	id_veterinario bigint,
	id_animal bigint
)








