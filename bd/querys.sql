use `pedidos-pizzeria`;
-- para cambios que involucren llaves
SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1;


select * from pizza;
select * from usuario;
select * from roles;
select * from formulario;
call SP_RolAccesoLista(1);
call SP_VentasRpt('2021-06-15', '2021-06-30'); # yyyy-mm-dd
call SP_CoberturaRpt('2021-06-15', '2021-06-30');
call SP_TipoPizzaLista();
update persona set idEstado = 6 where idPersona = 1;
SELECT SHA1('abc')