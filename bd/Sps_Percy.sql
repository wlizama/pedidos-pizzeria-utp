CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_ComprobantePedidoDetalle_cabecera`(
In idcomprobante int)
BEGIN
	select 
        c.IdComprobante, c.IdPedido, dp.IdDetallePedido, p.IdCliente, p.IdDireccionEnvio, 
        cli.IdPersona, per.nombres, per.apellidos, docide.numero as documentoIdentidad, dir.direccion, p.fechacreacion
    from comprobante c inner join detallepedido dp on c.IdPedido = dp.IdPedido
    inner join pedido p on dp.IdPedido = p.IdPedido
    join cliente cli on p.IdCliente = cli.IdCliente
    join persona per on cli.IdPersona = per.IdPersona
    join direccion dir on p.IdDireccionEnvio = dir.IdDireccion
    join documentoidentidad docide on per.IdDocumentoIdentidad = docide.IdDocumentoIdentidad
    where c.IdComprobante = idcomprobante;

END


CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_ComprobantePedidoDetalle_lista`(
In idcomprobante int)
BEGIN
select 
        c.IdComprobante, c.IdPedido, dp.IdDetallePedido, piz.IdPizza, piz.nombre as pizza,
        piz.IdTamanho, tam.nombre as tamanho, dp.cantidad, piz.precio
        
    from comprobante c inner join detallepedido dp on c.IdPedido = dp.IdPedido
    inner join pizza piz on dp.IdPizza = piz.IdPizza
    inner join tamanho tam on piz.IdTamanho = tam.IdTamanho
    where c.IdComprobante = idcomprobante;
END



CREATE PROCEDURE `SP_ComprobanteLista`(
    numero int
)
BEGIN
   declare numero_ini int default 0;
    declare numero_fin int default 999999;
    if numero <> 0 then
        set numero_ini = numero;
        set numero_fin = numero;
    end if;
    select 
        c.IdComprobante, c.numero, c.monto, per.Nombres, per.Apellidos, c.IdPedido, 
        e.nombre as estado, pe.IdCliente, cli.IdPersona
	from comprobante c join estado e on c.IdEstado = e.IdEstado
    join pedido pe on c.IdPedido = pe.IdPedido
    join cliente cli on pe.IdCliente = cli.IdCliente
    join persona per on cli.IdPersona = per.IdPersona
    where c.numero between numero_ini and numero_fin;
END ;;
