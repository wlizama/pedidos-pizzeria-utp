use `pedidos-pizzeria`;
-- para cambios que involucren llaves
SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1;


select * from estado;
select * from tipoestado;
select * from tipopersona;
select * from roles;
select * from comprobante;
select * from detallepedido;
select * from pizza;
select * from pedido;
select ifnull(max(numero), 0) +1 from pedido;
/*
delete from comprobante;
delete from pedido;
*/

/*Cannot add or update a child row: a foreign key constraint fails (`pedidos-pizzeria`.`detallepedido`, CONSTRAINT `DetallePedido_ibfk_1` FOREIGN KEY (`IdPedido`) REFERENCES `pedido` (`IdPedido`))*/

call SP_RolAccesoLista(1);
call SP_VentasRpt('2021-06-15', '2021-06-30'); # yyyy-mm-dd
call SP_CoberturaRpt('2021-06-15', '2021-06-30');
call SP_PizzaListaxNombre('pepp');
--
set @iddetallepedido = 0;
set @total = 0;
call `pedidos-pizzeria`.SP_PedidoDetalleInserta(@iddetallepedido, 2, 4, 5, @total);
select @iddetallepedido, @total;
--
update persona set idEstado = 6 where idPersona = 1;
SELECT SHA1('abc')