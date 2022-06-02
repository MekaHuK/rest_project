CREATE TABLE IF NOT EXISTS test_table_time
(
    id    SERIAL PRIMARY KEY ,
    content  VARCHAR(200) NOT NULL ,
    status VARCHAR(100) NOT NULL ,
    init_time TIMESTAMP(100) NOT NULL
);
--CREATE SEQUENCE transactions_id_seq START WITH 3 INCREMENT BY 1;