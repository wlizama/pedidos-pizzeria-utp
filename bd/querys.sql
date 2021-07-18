use `pedidos-pizzeria`;
-- para cambios que involucren llaves
SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1;


select * from estado;
select * from tipoestado;
select * from tipopersona;
select * from persona;
select * from roles;
select * from formulario;
select * from documentoIdentidad;
select * from tipodocumentoidentidad;
select * from tamanho;
select * from tipopizza;
select * from pizza;
select * from pedido;
select * from envio;
select * from detalleenvio;
select * from comprobante;
select ifnull(max(numero), 0) +1 from pedido;
/*
delete from comprobante;
delete from pedido;
*/
select * from usuario;
update usuario set contrasenha = '1234';

call SP_ComprobanteLista(0);
call SP_ComprobanteCabecera(6);
call SP_VentasRpt('2021-06-15', '2021-06-30'); # yyyy-mm-dd
call SP_CoberturaRpt('2021-06-15', '2021-06-30');
call SP_AccesoUsuario(1);
--
set @iddetallepedido = 0;
set @total = 0;
call `pedidos-pizzeria`.SP_PedidoDetalleInserta(@iddetallepedido, 2, 4, 5, @total);
select @iddetallepedido, @total;
--
update persona set idEstado = 6 where idPersona = 1;
SELECT SHA1('abc')

delete from tamanho where IdTamanho > 4;
SELECT * from pizza;

