CREATE
KEYSPACE IF NOT EXISTS STARWARS
WITH REPLICATION = {
        'class':'SimpleStrategy'
        ,'replication_factor': 1 }
     AND DURABLE_WRITES = true;

USE STARWARS;

CREATE TABLE PLANETA
(
    ID        UUID,
    NAME      TEXT,
    CLIMATE     TEXT,
    TERRAIN   TEXT,
    FILMCOUNT INT,
    PRIMARY KEY (ID)
);


CREATE
CUSTOM INDEX ON PLANETA (NAME) USING 'org.apache.cassandra.index.sasi.SASIIndex'
with options = {
        'mode': 'CONTAINS',
        'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.StandardAnalyzer',
        'analyzed': 'true',
        'tokenization_skip_stop_words': 'and, the, or',
        'tokenization_enable_stemming': 'true',
        'tokenization_normalize_lowercase': 'true',
        'tokenization_locale': 'en'
        };


INSERT INTO PLANETA(id, nome, terreno, clima)
VALUES (uuid(), 'Solorize', 'Terrão', 'Tropical');
INSERT INTO PLANETA(id, nome, terreno, clima)
VALUES (uuid(), 'Kadenck', 'Gelo', 'Frio');
INSERT INTO PLANETA(id, nome, terreno, clima)
VALUES (uuid(), 'Jupilany', 'Arenoso', 'Caatinga');
