Esquema do banco de dados 

CREATE TABLE tb_customer
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);
