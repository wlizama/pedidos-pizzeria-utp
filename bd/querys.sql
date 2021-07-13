use `pedidos-pizzeria`;
-- para cambios que involucren llaves
SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1;


select * from estado;
select * from tipoestado;
select * from tipopersona;
select * from roles;
select * from tipodocumentoidentidad;

call SP_RolAccesoLista(1);
call SP_VentasRpt('2021-06-15', '2021-06-30'); # yyyy-mm-dd
call SP_CoberturaRpt('2021-06-15', '2021-06-30');
call SP_ClienteLista(0, '');
call SP_DistritoLista();
update persona set idEstado = 6 where idPersona = 1;
SELECT SHA1('abc')