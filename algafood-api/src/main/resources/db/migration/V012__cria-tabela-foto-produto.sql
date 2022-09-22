CREATE TABLE foto_produto (
produto_id BIGINT NOT NULL AUTO_INCREMENT,
nome_arquivo VARCHAR(100) NOT NULL,
descricao VARCHAR(60),
content_type VARCHAR(20) NOT NULL,
tamanho INT NOT NULL,

PRIMARY KEY (produto_id),
CONSTRAINT fk_foto_produto_produto FOREIGN KEY (produto_id) REFERENCES produto (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;