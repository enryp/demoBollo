# demoBollo

Applicazione sviluppata con Springboot che permette di gestire l'anagrafica delle Targhe auto e controllare lo stato dei pagamenti del Bollo con la Regione Piemonte.

Creare l'ambiente in locale con Docker Compose, eseguendo il comando:

docker-compose up -d

In questo modo verranno scaricate e avviate le immagini di:
- DB Postgres per il salvataggio delle anagrafiche
- server Keycloak per la gestione dell'autenticazione

Accedere alla console di amministrazione di keycloak per verificarne lo stato (user:admin - pwd: admin):
usando l'indirizzo ip del container docker in locale 
http://172.30.231.105:8085/admin/master/console

Compilare il progetto con maven indicando nella variabile HOST l'indirizzo ip del container docker in locale interfa:
mvn -DHOST=172.30.231.105 clean package

Avviare il servizio in locale con:
java -jar -DHOST=172.30.231.105 target/demoBollo-0.0.1-SNAPSHOT.jar
oppure
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DHOST=172.30.231.105"

Dal browser aprire la pagina di autenticazione
http://172.30.231.105:8080/index.html
autenticandosi con le credenziali:
user: enricopero
pwd: password





