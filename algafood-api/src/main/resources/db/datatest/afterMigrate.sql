SET foreign_key_checks = 0;

DELETE FROM cozinha;
DELETE FROM cidade;
DELETE FROM estado;
DELETE FROM forma_pagamento;
DELETE FROM grupo;
DELETE FROM grupo_permissao;
DELETE FROM permissao;
DELETE FROM produto;
DELETE FROM restaurante;
DELETE FROM restaurante_forma_pagamento;
DELETE FROM usuario;
DELETE FROM usuario_grupo;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Italiana');
insert into cozinha (id, nome) values (4, 'Argentina');
insert into cozinha (id, nome) values (5, 'Brasileira');

insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartao de Credito');
insert into forma_pagamento (descricao) values ('Cartao de Debito');
insert into forma_pagamento (descricao) values ('PIX');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_cep, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo) values ('Fasano', 7.5, 3, 'Av Sete de Setembro', '2030', '', '02036022', 'Santana', 3, utc_timestamp, utc_timestamp, true);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_logradouro, endereco_numero, endereco_complemento, endereco_cep, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo) values ('Terraço Italia', 12.5, 3, 'Av Nove de Julho', '1922', '', '03409000', 'Bela Cintra', 3, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (3, 'Outback', 12, 3, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true);

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (1,1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (1,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (1,3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (1,4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (2,3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (2,4);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (3,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (3,3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (4,1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (4,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (5,1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (5,2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id ) values (6,3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Prato A', 'Prato feito de letra A', 49.99, 1, 3), ('Prato B', 'Prato feito de letra B', 59.99, 1, 2), ('Prato C', 'Prato feito de letra C', 69.99, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretaria'), ('Analista'), ('Auxiliar');

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);

SET foreign_key_checks = 1;
