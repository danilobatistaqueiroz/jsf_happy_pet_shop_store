--installing postgresql on windows--
--download it: https://www.enterprisedb.com/postgresql-959-binaries-win32?ls=Crossover&type=Crossover
--initialize server: initdb c:\users\danilo.queiroz\Downloads\pgsql\data
--run server:   pg_ctl -D "c:\users\danilo.queiroz\Downloads\pgsql\data" -l logfile start
--create db:    createdb DBCOM
--create user:  createuser --interactive -l -P -s -d postgres
--createuser --interactive -l -P -s -d usercom

--installing postgresql on ubuntu--
--update repositories: sudo apt-get update
--install: sudo apt-get install postgresql postgresql-contrib
--test the user postgres: sudo -i -u postgres
--enter on terminal: pgsql
--exit from postgre terminal: \q
--exit from postgres session: exit
--add a new linux user: sudo adduser usercom
--create a new db: createdb DBCOM
--psql --dbname=DBCOM


--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO usercom;

--configuring wildfly's datasource (http://www.mastertheboss.com/jboss-server/jboss-datasource/configuring-a-datasource-with-postgresql-and-jboss-wildfly)
--./standalone.sh
--./jboss-cli.sh
-- module add --name=org.postgres --resources=/home/bee/git/jsf_happy_pet_shop_store/wildflyconfig/modules/org/postgres/postgresql-42.1.4.jar --dependencies=javax.api,javax.transaction.api
-- /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
-- data-source add --jndi-name=java:/PostGreDS --name=PostgrePool --connection-url=jdbc:postgresql://localhost/postgres --driver-name=postgres --user-name=usercom --password=123

--DROP TABLE Product;
--DROP TABLE UserRoles;
--DROP TABLE Users;

--if you want tables with capitalized names, its necessary to use double cotes in everywhere that refers to tables and columns:
-- uppon of model: @Table(name = "\"Product\"")
-- when creating the table: CREATE TABLE "Product" (...
-- I prefer to leave the code clean without this type of information, and assumed all tables and columns with lowercase names


CREATE TABLE Product (
  id serial PRIMARY KEY,
  description varchar(255) NOT NULL,
  name varchar(50) NOT NULL,
  price decimal(10,2) NOT NULL
)

CREATE TABLE Vendor (
  id int PRIMARY KEY,
  name varchar(50) NOT NULL
)

CREATE TABLE UserRoles (
  username varchar(255) NOT NULL,
  role varchar(32) NOT NULL
)

CREATE TABLE Users (
  username varchar(255) NOT NULL,
  passwd varchar(255) NOT NULL,
  PRIMARY KEY (username)
)


INSERT INTO Product VALUES (1,'Training Pads are a convenient and easy way to train your puppy.','Top Paw Dog Pad',5.77),(2,'Ultra Soft Faux Fleece & Easy to assemble','Armarkat Cat Tree',54.33),(3,'Wider and deeper stair landings for small and large dogs','Pet Gear Easy Step II Pet Stairs',54.33),(4,'It is made out of durable, heavy duty plasctic','Slow Feed Non-Skid Dog Bowl',4.00),(5,'It is a unique indoor resting area your cat will love','Cat Window Perch',31.00),(6,'Made with sturdy steel & strong blow-molded plastic','Cat Window Perch',10.31),(7,'Say good-bye to bothersome crate!','Super Dog Crate',18.10),(8,'Attractive design & easy-to-use features','Pet Nail Trimmer',20.50),(9,'It helps you remove the loose, dead hair','Furminator deshedding tool',36.99),(10,'It helps you remove the loose, dead hair','Furminator deshedding Pro',55.00);

INSERT INTO vendor VALUES (1, 'apple')
INSERT INTO vendor VALUES (3, 'lg')

INSERT INTO UserRoles VALUES ('adam','admin'),('tomy','commonUser'),('mary','commonUser'),('batista','admin'),('queiroz','admin');


INSERT INTO Users VALUES ('adam','A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ='),('batista','A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ='),('mary','A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ='),('queiroz','A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ='),('tomy','A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=');



--select * from Product






