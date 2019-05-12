
create if not exists table usuario(
	id bigint primary key AUTO_INCREMENT,
	nome varchar(255) not null,
	perfil varchar(255) not null,
	senha varchar(255) not null, 
	login varchar(255) not null,
	telefone varchar(255),
	email varchar(255),
	status varchar(255) not null
);

create table categoria(
	id bigint primary key AUTO_INCREMENT,
	nome varchar(255) not null,
	status varchar(255) not null
);

create table conta(
	id bigint primary key AUTO_INCREMENT,
	descricao varchar(255) not null,
	valor decimal(9,2) not null,
	categoria_id bigint not null, 
	tipo varchar(255) not null,
	data_pagamento date,
	data_vencimento date not null,
	status varchar(255) not null,
	constraint fk_conta_categoria foreign key (categoria_id) references categoria (id)
);

create table auditoria(
	id bigint primary key AUTO_INCREMENT,
	acao varchar(255) not null,
	usuario_id bigint not null,
	data datetime not null,
	registro_antes longtext, 
	registro_depois longtext,
	constraint fk_auditoria_usuario foreign key (usuario_id) references usuario (id)
);


insert usuario (nome, perfil, senha, login, status, telefone, email)
values ('Miller Wheygry', 'ADMINISTRADOR', '123', 'miller', 'ATIVO', '(88)1234-5678', 'miller@mail.com'); 

INSERT INTO auditoria (acao, usuario_id, data, registro_antes, registro_depois) 
values ('LOGAR', 1, '2019-05-05 15:37:01', null, null);


select auditoria.*, usuario.id, usuario.nome 
from auditoria 
inner join usuario 
on auditoria.usuario_id = usuario.id
where usuario.id = 1;
 