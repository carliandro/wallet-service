CREATE TABLE IF NOT EXISTS currency
(
    id SERIAL PRIMARY KEY,
    name character varying(3) UNIQUE NOT NULL,
    last_updated TIMESTAMP DEFAULT now(),
	last_updated_by VARCHAR DEFAULT 'recarga-pay'
);


CREATE TABLE IF NOT EXISTS  wallet
(
id SERIAL PRIMARY KEY,
user_id VARCHAR NOT NULL,
balance NUMERIC(15,2) DEFAULT 0 NOT NULL,
currency_id	integer REFERENCES currency (id) NOT NULL,
last_updated TIMESTAMP DEFAULT now(),
last_updated_by VARCHAR DEFAULT 'recarga-pay'
);


CREATE TABLE IF NOT EXISTS transaction_type
(
id VARCHAR PRIMARY KEY,
description TEXT,
last_updated TIMESTAMP DEFAULT now(),
last_updated_by VARCHAR DEFAULT 'recarga-pay'
);


CREATE TABLE IF NOT EXISTS transaction
(
id SERIAL PRIMARY KEY,
global_id VARCHAR UNIQUE NOT NULL,
type_id VARCHAR NOT NULL REFERENCES transaction_type (id),
amount NUMERIC(15,2) NOT NULL,
wallet_id integer REFERENCES wallet(id),
currency_id	integer REFERENCES currency (id) NOT NULL,
description TEXT,
last_updated TIMESTAMP DEFAULT now(),
last_updated_by VARCHAR DEFAULT 'recarga-pay'
);