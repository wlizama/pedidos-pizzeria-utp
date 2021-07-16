
-- tabla Formulario
use `pedidos-pizzeria`;
insert into Formulario ( IdFormulario, nombre )
values 
( 0, 'Menu Pedidos' ),
( 0, 'Menu Delivery' ),
( 0, 'Menu Mantenimiento' ),
( 0, 'Menu Reportes' ),
( 0, 'Menu Mi sessión' );
select * from Formulario;

-- tabla rol inicial
insert into Roles ( IdRol, nombre )
values 
( 0, 'Super usuario' ),
( 0, 'Cajero' ),
( 0, 'Administrador' );
select * from Roles;

-- tabla Accesos
insert into Acceso ( IdAcceso, IdFormulario, IdRol )
values 
( 0, 1, 1),
( 0, 2, 1),
( 0, 3, 1),
( 0, 4, 1),
( 0, 5, 1);
select * from Acceso;

-- tabla TipoPersona
insert into TipoPersona ( IdTipoPersona, nombre )
values 
( 0, 'Gerencia' ),
( 0, 'Cajero' ),
( 0, 'Delivery' ),
( 0, 'Cliente' ),
( 0, 'Otros' );
select * from TipoPersona;

-- tabla rol inicial
insert into TipoDocumentoIdentidad ( IdTipoDocIdentidad, nombre, cantidadCaracteres )
values
(0, 'L.E / DNI', 8),
(0, 'CARNET EXT.', 12),
(0, 'RUC', 11),
(0, 'PASAPORTE', 12),
(0, 'PART. DE NACIMIENTO-IDENTIDAD', 15),
(0, 'OTROS', 15);
select * from TipoDocumentoIdentidad;

-- Tabla estados 
/*
- Estado () , 5 items.
	* Generado (Pedido/Envio)
	* Listo para entrega
	* En Camino
	* Finalizado
	* Finalizado con incidencias
	* Activo (Usuario)
	* Inactivo
	* Pendiente (Comprobante)
	* Finalizado
	* Anulado
	* Disponible (Pizza)
	* No Disponible
*/
insert into TipoEstado( IdTipoEstado, nombre )
values
(0, 'Pedido / Envio'),
(0, 'Usuario'),
(0, 'Comprobante'),
(0, 'Pizza');
select * from TipoEstado;

insert into Estado( IdEstado, nombre, IdTipoEstado )
values
(0, 'Generado', 1),
(0, 'Listo para entrega', 1),
(0, 'En Camino', 1),
(0, 'Finalizado', 1),
(0, 'Finalizado con incidencias', 1),
(0, 'Activo', 2),
(0, 'Inactivo', 2),
(0, 'Pendiente', 3),
(0, 'Finalizado', 3),
(0, 'Anulado', 3),
(0, 'Disponible', 4),
(0, 'No Disponible', 4);
select * from Estado;

-- super usuario de sistema
insert into DocumentoIdentidad ( IdDocumentoIdentidad, numero, IdTipoDocIdentidad )
value( 0, 'XXXXXXXXXXXXXXX', 6 );
insert into Persona ( IdPersona, nombres, apellidos, telefono, IdTipoPersona, IdDocumentoIdentidad, IdEstado )
value(0, 'Super Usuario de Sistema', null, null, 5, 1, 6);
insert into Usuario ( IdUsuario, nombreUsuario, contrasenha, IdPersona, IdRol )
value( 0, 'suuser' , SHA1('123456'), 1, 1 );

insert into distrito (nombre, cobertura)
values 
('Ancón', 0),
('Ate Vitarte', 0),
('Barranco', 0),
('Breña', 0),
('Carabayllo', 0),
('Chaclacayo', 0),
('Chorrillos', 0),
('Cieneguilla', 0),
('Comas', 0),
('El Agustino', 0),
('Independencia', 0),
('Jesús María', 0),
('La Molina', 0),
('La Victoria', 0),
('Lima', 0),
('Lince', 0),
('Los Olivos', 0),
('Lurigancho', 0),
('Lurín', 0),
('Magdalena del Mar', 0),
('Miraflores', 0),
('Pachacamac', 0),
('Pucusana', 0),
('Pueblo Libre', 0),
('Puente Piedra', 0),
('Punta Hermosa', 0),
('Punta Negra', 0),
('Rímac', 0),
('San Bartolo', 0),
('San Borja', 0),
('San Isidro', 0),
('San Juan de Lurigancho', 0),
('San Juan de Miraflores', 0),
('San Luis', 0),
('San Martín de Porres', 0),
('San Miguel', 0),
('Santa Anita', 0),
('Santa María del Mar', 0),
('Santa Rosa', 0),
('Santiago de Surco', 0),
('Surquillo', 0),
('Villa El Salvador', 0),
('Villa María del Triunfo', 0);

insert into tipocomprobante (nombre, IdEstado)
values ('Boleta', 6);

-- AGREGAR VALOR POR DEFECTO A COMPROBANTE Y PEDIDO y envio
-- cambiar tipo de dato de time a datetime a envio
-- valores para prueba busqueda pizza
insert into tamanho (nombre, cantidadPorciones)
values
('Pequeña', 8),
('Mediana', 12),
('Grande', 14),
('Extra Grande', 16);
select * from tamanho;
select * from tipopizza;
select * from pizza;
insert into pizza (nombre, precio, IdTipoPizza, IdTamanho, IdEstado)
values
('Americana', 15.90, 9, 1, 11),
('Americana', 31.90, 9, 3, 11),
('Americana', 42.90, 9, 4, 11),
('Pepperoni', 21.50, 2, 2, 11),
('Pepperoni', 30.50, 2, 3, 11),
('Pepperoni', 40.50, 2, 4, 11),
('Súper Margarita 6 Quesos', 15.90, 1, 1, 11),
('Súper Margarita 6 Quesos', 25.90, 1, 2, 11),
('Súper Margarita 6 Quesos', 35.90, 1, 3, 11),
('Súper Margarita 6 Quesos', 45.90, 1, 4, 11),
('La Rocoto Relleno', 22.80, 7, 2, 11),
('La Rocoto Relleno', 32.80, 7, 3, 11),
('La Rocoto Relleno', 45.80, 7, 4, 11),
('All The Meats', 15.60, 5, 1, 11),
('All The Meats', 28.80, 5, 2, 11),
('All The Meats', 36.80, 5, 4, 11);