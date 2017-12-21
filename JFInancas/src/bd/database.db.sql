BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `usuarios` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nome`	TEXT NOT NULL UNIQUE,
	`saldo`	REAL,
	`id_registradora`	INTEGER,
	`senha`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `tipos` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nome`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `registradoras` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT
);
CREATE TABLE IF NOT EXISTS `movimentacoes` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`id_registradora`	INTEGER,
	`id_tipo`	INTEGER,
	`nome`	TEXT NOT NULL,
	`valor`	REAL NOT NULL,
	`data`	TEXT NOT NULL,
	`categoria`	TEXT NOT NULL CHECK(categoria = 'Ganho' OR categoria = 'Gasto'),
	FOREIGN KEY(`id_tipo`) REFERENCES `tipos`(`id`)
);
CREATE TABLE IF NOT EXISTS `limitadores` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`id_registradora`	INTEGER,
	`id_tipo`	INTEGER,
	`nome`	TEXT NOT NULL,
	`valor`	REAL NOT NULL,
	`inicio`	TEXT NOT NULL,
	`fim`	TEXT NOT NULL,
	`categoria`	TEXT NOT NULL CHECK(categoria = 'Meta' OR categoria = 'Teto'),
	FOREIGN KEY(`id_tipo`) REFERENCES `tipos`(`id`)
);
INSERT INTO tipos (nome) values ("Alimentação");
INSERT INTO tipos (nome) values ("Transporte");
INSERT INTO tipos (nome) values ("Entretenimento");
INSERT INTO tipos (nome) values ("Medicamentos");
INSERT INTO tipos (nome) values ("Saude");
INSERT INTO tipos (nome) values ("Contas");
INSERT INTO tipos (nome) values ("Livros");
COMMIT;
