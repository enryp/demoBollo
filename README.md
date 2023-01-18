# demoBollo

Applicazione sviluppata con Springboot che permette di gestire l'anagrafica delle Targhe auto e controllare lo stato dei pagamenti del Bollo con la Regione Piemonte.

Creare l'ambiente in locale con Docker Compose, eseguendo il comando:

docker-compose up -d

In questo modo verranno scaricate e avviate le immagini di:
- DB Postgres per il salvataggio delle anagrafiche
- server Keycloak per la gestione dell'autenticazione

Accedere alla console di amministrazione di keycloak per verificarne lo stato (user:admin - pwd: admin):
usando l'indirizzo ip del container docker in locale 
http://localhost:8085/admin/master/console

Modificare la property spring.datasource.url nel file application.properties impostando l'IP docker del postgres
spring.datasource.url=jdbc:postgresql://<IP_DOCKER>:5432/demodb

Compilare il progetto con maven indicando nella variabile HOST l'indirizzo ip del container docker in locale interfa:
mvn clean package

Avviare il servizio in locale con:
mvn spring-boot:run

Dal browser aprire la pagina di autenticazione
http://localhost:8080/index.html
autenticandosi con le credenziali:
user: enricopero
pwd: password





