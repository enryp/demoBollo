CREATE SEQUENCE public.veicoli_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.veicolo (
	targa varchar NOT NULL,
	tipo varchar NULL,
	codice_fiscale varchar NULL,
	cilindrata int4 NULL,
	immatricolazione timestamp NULL,
	id_user varchar NULL,
	CONSTRAINT veicolo_pk PRIMARY KEY (targa)
);

