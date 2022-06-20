-- creamos la bd
CREATE DATABASE EXAM3DAW1;
-- activamos la bd
USE EXAM3DAW1;
--
create table tb_Carreras(
idcarrera	int not null primary key,
descripcion varchar(45)
);
--
insert into   tb_Carreras   values (1, 'computación');
insert into   tb_Carreras   values (2, 'diseño');
insert into   tb_Carreras   values (3, 'contabilidad');
insert into   tb_Carreras   values (4, 'RRHH');
--
create table tb_reserva(
id_reser char(5) not null,
nombre varchar(45) ,
apellido varchar(25),
idcarrera int,
fecha  date DEFAULT NULL,
primary key (id_reser), 
constraint fk_carrera foreign key (idcarrera) references tb_Carreras(idcarrera)
);
--
insert into  tb_reserva values ('R0001', 'Diana', 'Sanchez',1,'2021-06-15');
insert into  tb_reserva values ('R0002', 'Marta', 'Gomez',2,'2021-06-10');
insert into  tb_reserva values ('R0003', 'Kelly', 'Palomino',3,'2021-06-20');
insert into  tb_reserva values ('R0004', 'Aaron', 'Aponte',4,'2021-06-25');
--


