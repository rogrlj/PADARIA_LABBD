CREATE TABLE produtos (
                          id		int 		NOT NULL AUTO_INCREMENT,
                          nome	VARCHAR(255) NOT NULL,
                          preco	DECIMAL(7,2) NOT NULL,
                          dataInclusao 	DATE	NOT NULL,
                          descricao	VARCHAR(255),
                          primary key (id)
)