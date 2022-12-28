CREATE TABLE public.veicolo (
	id int4 NOT NULL,
	targa varchar NULL,
	tipo varchar NULL,
	codice_fiscale varchar NULL,
	cilindrata int4 NULL,
	immatricolazione timestamp NULL,
	CONSTRAINT veicolo_pk PRIMARY KEY (id)
);
