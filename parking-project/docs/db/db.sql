CREATE TABLE user (
	id integer PRIMARY KEY AUTOINCREMENT,
	first_name varchar NOT NULL,
	last_name varchar NOT NULL
);

CREATE TABLE car (
	id integer PRIMARY KEY AUTOINCREMENT,
	owner_id integer REFERENCES user(id),
	model_id integer REFERENCES model(id),
	vin varchar NOT NULL
);

CREATE TABLE brand (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar NOT NULL
);

CREATE TABLE model (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar  NOT NULL,
	brand_id integer REFERENCES brand(id)
);

CREATE TABLE place (
	id integer PRIMARY KEY AUTOINCREMENT,
	busy integer NOT NULL
);

CREATE TABLE car_2_place (
	id integer PRIMARY KEY AUTOINCREMENT,
	place_id integer REFERENCES place(id),
	car_id integer REFERENCES car(id),
	contract_start datetime NOT NULL,
	contract_end datetime NOT NULL
);
