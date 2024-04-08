CREATE DATABASE aerolinea;

use aerolinea;

CREATE TABLE avion(
id_avion INT primary key auto_increment,
modelo VARCHAR(40),
Capacidad INT
);


CREATE TABLE pasajero(
id_pasajero INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(30),
apellido VARCHAR(30),
documneto_identidad VARCHAR(20)
);

CREATE TABLE vuelo(
id_vuelo INT PRIMARY KEY AUTO_INCREMENT,
destino VARCHAR(40),
fecha_salida DATE, 
hora_salida TIME, 
id_avion INT NOT NULL,
constraint kf_vuelo_avion foreign key(id_vuelo) references avion(id_avion) ON DELETE CASCADE
);

CREATE TABLE reservacion(
id_reservacion INT PRIMARY KEY AUTO_INCREMENT,
fecha_reservacion date,
asiento varchar(10),
id_pasajero INT NOT NULL, 
constraint fk_reservacion_pasajero foreign key(id_pasajero) references pasajero(id_pasajero) on delete cascade, 
id_vuelo INT NOT NULL,
constraint fk_reservacion_vuelo foreign key(id_vuelo) references vuelo(id_vuelo) on delete cascade
);

