
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
value( 0, 'suuser' , SHA1('123456'), 1, 1 )


