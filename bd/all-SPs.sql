USE `pedidos-pizzeria`;
--
-- Dumping routines for database 'pedidos-pizzeria'
--

DROP PROCEDURE IF EXISTS `SP_AccesoUsuario`;
DELIMITER ;;
CREATE PROCEDURE `SP_AccesoUsuario`(
    IdUsuario int
)
BEGIN
    select 
        f.IdFormulario,
        f.nombre
    from formulario f 
    join acceso a on f.IdFormulario = a.IdFormulario
    join roles r on a.IdRol = r.IdRol 
    join usuario u on r.IdRol = u.IdRol
    where u.IdUsuario = IdUsuario;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Cliente`;
DELIMITER ;;
CREATE PROCEDURE `SP_Cliente`(
In idcliente int)
BEGIN
    select p.Nombres, p.Apellidos, p.Telefono, di.numero as Documento, 
    di.IdTipoDocIdentidad as TipoDocumentoIdentidad
    from cliente c inner join persona p on c.IdPersona = p.IdPersona
    inner join documentoIdentidad di on p.IdDocumentoIdentidad = di.IdDocumentoIdentidad
    inner join direccion dir on c.IdCliente = dir.IdCliente
    where c.IdCliente = idcliente;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClienteDireccion`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteDireccion`(
In iddireccioncliente int)
BEGIN
    select per.nombres + " " + per.apellidos as Cliente, dir.direccion, dir.principal, dir.referencia
    from direccion dir inner join cliente cli on dir.IdCliente = cli.IdCliente
    inner join persona per on cli.IdPersona = per.IdPersona
    where dir.IdDireccion = iddireccion;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClienteDireccionInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteDireccionInserta`(
In direccion varchar(100),
In referencia varchar(150),
In idcliente int,
In iddistrito int,
In principal tinyint,
Out codigo int
)
BEGIN
    DECLARE last_insert_id INT;
    
    insert into direccion (direccion, referencia, IdCliente, IdDistrito, principal, IdEstado)
    values (direccion, referencia, idcliente, iddistrito, principal, 1); -- 1 = activo
    
    SET codigo = LAST_INSERT_ID();
    
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClienteDireccionLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteDireccionLista`(
In idcliente int)
BEGIN
    select idDireccion, direccion, referencia, principal
    from direccion
    where IdCliente = idcliente;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClienteDireccionModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteDireccionModifica`(
In iddireccion int,
In direccion varchar(100),
In referencia varchar(150),
In iddistrito int,
In principal tinyint)
BEGIN
    update direccion set direccion = direccion, referencia = referencia, IdDistrito = iddistrito, principal = principal
    where IdDireccion = iddireccion;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClienteInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteInserta`(
In nombres varchar(100),
In apellidos varchar(100),
In IdTipoPersona int,
In IdDocumentoIdentidad int,
In IdEstado int,
In telefono varchar(10),
OUT idcliente int
)
BEGIN

    DECLARE last_insert_id INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;
        insert into persona (nombres, apellidos, IdTipoPersona, IdDocumentoIdentidad, IdEstado, telefono)
        values (nombres, apellidos, IdTipoPersona, IdDocumentoIdentidad, IdEstado, telefono);
        
        SET last_insert_id = LAST_INSERT_ID(); -- assignment
        
        insert into cliente(IdPersona) values (last_insert_id);   
        
        SET idcliente = LAST_INSERT_ID();
        
    COMMIT;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClienteLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteLista`(
In idtipoDocIdentidad int,
In numero varchar(15))
BEGIN
    select di.numero as NumeroDocumento, p.Nombres + " " + p.Apellidos as Cliente, 
    dir.direccion, p.IdEstado, p.Telefono
    from cliente c inner join persona p on c.IdPersona = p.IdPersona
    inner join documentoIdentidad di on p.IdDocumentoIdentidad = di.IdDocumentoIdentidad
    inner join direccion dir on c.IdCliente = dir.IdCliente
    where di.IdTipoDocIdentidad = idtipoDocIdentidad and
    di.numero = numero;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClienteModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteModifica`(
In nombres varchar(100),
In apellidos varchar(100),
In iddocumentoidentidad int,
In telefono varchar(10),
In numero varchar(15),
In idcliente int)
BEGIN
    update cliente cli
        inner join persona per on cli.IdPersona = per.IdPersona
        inner join documentoidentidad di on per.IdDocumentoIdentidad = di.IdDocumentoIdentidad
        set per.nombres = nombres, per.apellidos = apellidos, per.telefono = telefono,
        per.IdDocumentoIdentidad = iddocumentoidentidad, di.numero = numero
    where cli.idCliente = idcliente;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ClientexTipoDocNumero`;
DELIMITER ;;
CREATE PROCEDURE `SP_ClientexTipoDocNumero`(
In idtipodocidentidad int,
In numero varchar(15))
BEGIN
    select di.IdTipoDocIdentidad as TipoDocumento, di.numero, p.IdDireccionEnvio,
    com.IdComprobante, p.IdEstado, per.nombres, per.telefono, p.observaciones
    from pedido p inner join cliente cli on p.IdCliente = cli.IdCliente
    inner join persona per on cli.IdPersona = per.IdPersona
    inner join documentoIdentidad di on p.IdDocumentoIdentidad = di.IdDocumentoIdentidad 
    inner join comprobante com on p.IdPedido = com.IdPedido
    where di.IdTipoDocIdentidad = idtipodocidentidad and di.numero = numero ;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_CoberturaRpt`;
DELIMITER ;;
CREATE PROCEDURE `SP_CoberturaRpt`(
    fechaCreacion_inicio date,
    fechaCreacion_fin date
)
BEGIN
    select
        dis.nombre as distrito,
        count(dp.cantidad)
        -- falta monto
    from detallepedido dp
    join pedido p on dp.IdPedido = p.IdPedido
    join pizza pz on dp.IdPizza = pz.IdPizza
    join direccion d on p.IdDireccionEnvio = d.IdDireccion
    join distrito dis on d.IdDistrito = dis.IdDistrito
    where p.fechacreacion between fechaCreacion_inicio and fechaCreacion_fin
    group by p.IdDireccionEnvio, dp.cantidad;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Comprobante`;
DELIMITER ;;
CREATE PROCEDURE `SP_Comprobante`(
    IdComprobante int
)
BEGIN
    select 
        c.IdComprobante,
        c.numero,
        c.fechaEmision,
        c.horaEmision,
        c.IdTipoComprobante,
        tc.nombre as tipoComprobante,
        c.monto,
        c.IdPedido,
        c.IdEstado,
        e.nombre as estado
    from comprobante c
    join tipocomprobante tc on c.IdTipoComprobante = tc.IdTipoComprobante
    join estado e on c.IdEstado = e.IdEstado
    where c.IdComprobante = IdComprobante;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ComprobanteInsertar`;
DELIMITER ;;
CREATE PROCEDURE `SP_ComprobanteInsertar`(
In fechaemision date,
In horaemision time,
In idtipocomprobante int,
In monto decimal,
In idpedido int,
In idestado int,
Out idcomprobante int,
Out numero int)
BEGIN
    insert into comprobante (fechaemision, horaemision, idtipocomprobante, monto, idpedido, idestado)
    values (fechaEmision, horaEmision, IdTipoComprobante, monto, IdPedido, IdEstado);
    
    set idcomprobante = LAST_INSERT_ID();
    
    select numero into numero from comprobante where IdComprobante = idcomprobante;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ComprobanteLista`;
DELIMITER ;;
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
        c.IdComprobante,
        c.numero,
        c.fechaEmision,
        c.horaEmision,
        c.IdTipoComprobante,
        tc.nombre as tipoComprobante,
        c.monto,
        c.IdPedido,
        c.IdEstado,
        e.nombre as estado
    from comprobante c
    join tipocomprobante tc on c.IdTipoComprobante = tc.IdTipoComprobante
    join estado e on c.IdEstado = e.IdEstado
    where c.numero between numero_ini and numero_fin;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_ComprobanteModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_ComprobanteModifica`(
In fechaemision date,
In horaemision time,
In idtipocomprobante int,
In monto decimal,
In idpedido int,
In idestado int,
In idcomprobante int)
BEGIN
    update comprobante set fechaEmision = fechaemision, horaEmision = horaemision, 
    IdTipoComprobante = idtipocomprobante, monto = monto, IdEstado = idestado
    where IdComprobante = idcomprobante;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Distrito`;
DELIMITER ;;
CREATE PROCEDURE `SP_Distrito`(
    IdDistrito int
)
BEGIN
    select 
        d.IdDistrito,
        d.nombre,
        d.cobertura
    from distrito d
    where d.IdDistrito = IdDistrito;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_DistritoInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_DistritoInserta`(
    out IdDistrito int,
    nombre varchar(50),
    cobertura tinyint(1)
)
BEGIN
    insert into distrito (
        nombre,
        cobertura
    )
    values (
        nombre,
        cobertura
    );
    
    SET IdDistrito = LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_DistritoLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_DistritoLista`()
BEGIN
    select 
        IdDistrito,
        nombre,
        cobertura
    from distrito d;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_DistritoModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_DistritoModifica`(
    IdDistrito int,
    nombre varchar(50),
    cobertura tinyint(1)
)
BEGIN
    update distrito d
        set d.nombre = nombre,
        d.cobertura = cobertura
    where d.IdDistrito = IdDistrito;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Envio`;
DELIMITER ;;
CREATE PROCEDURE `SP_Envio`(
In idEnvio int)
BEGIN
select e.IdEnvio, e.hora_inicio, e.hora_fin, e.IdEstado, est.nombre as estado, p.nombres + " " + p.apellidos as repatidor
from envio e inner join estado est on e.IdEstado = est.IdEstado
inner join persona p on e.IdPersona = p.IdPersona
where e.IdEnvio = idenvio;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EnvioDetalle`;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioDetalle`(
In iddetalleenvio int
)
BEGIN
    select de.IdEnvio, de.IdPedido, per.nombres + " " + per.apellidos as Cliente, dir.direccion, p.IdEstado,
    p.FechaCreacion, p.HoraCreacion
    from detalleenvio de inner join pedido p on de.IdPedido = p.IdPedido
    inner join cliente cli on p.IdCliente = cli.IdCliente
    inner join persona per on cli.IdPersona = per.Idpersona
    inner join direccion dir on cli.IdCliente = dir.IdCliente
    where IdDetalleEnvio = iddetalleenvio;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EnvioDetalleEliminar`;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioDetalleEliminar`(
In iddetalleenvio int)
BEGIN
    delete from detalleenvio where IdDetalleEnvio = iddetalleenvio;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EnvioDetalleInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioDetalleInserta`(
In hora_fin time, 
In observaciones varchar(100) , 
In idenvio int, 
In idpedido int
)
BEGIN
    
    insert into detalleenvio (hora_Fin, observaciones, IdEnvio, IdPedido)
    values (hora_fin, observaciones, idenvio, idpedido);
    
    SELECT LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EnvioDetalleLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioDetalleLista`(
In idenvio int
)
BEGIN
    select de.IdEnvio, de.IdPedido, per.nombres + " " + per.apellidos as Cliente, dir.direccion, p.IdEstado,
    p.FechaCreacion, p.HoraCreacion
    from detalleenvio de inner join pedido p on de.IdPedido = p.IdPedido
    inner join cliente cli on p.IdCliente = cli.IdCliente
    inner join persona per on cli.IdPersona = per.Idpersona
    inner join direccion dir on cli.IdCliente = dir.IdCliente
    where IdEnvio = idenvio;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EnvioInsertar`;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioInsertar`(
In horainicio time, 
In horafin time, 
In IdPersona int,
In IdEstado int)
BEGIN
    insert into envio (hora_inicio, hora_fin, IdPersona, IdEstado)
    values (horainicio, horafin, IdPersona, IdEstado);
    
    SELECT LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EnvioLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioLista`(
In estado int)
BEGIN
select e.IdEnvio, e.horainicio, e.horafin, e.IdEstado, est.nombre as estado
from envio e inner join estado est on e.IdEstado = est.IdEstado
where IdEstado = estado;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EnvioModificar`;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioModificar`(
In idenvio int,
In horainicio time, 
In horafin time, 
In IdPersona int,
In IdEstado int)
BEGIN
    update envio set hora_inicio = horainicio, hora_fin = horafin, IdPersona = IdPersona, IdEstado = IdEstado
    where IdEnvio = idenvio;
        
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_EstadoLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_EstadoLista`(
    IdTipoEstado int
)
BEGIN
    select 
        e.IdEstado,
        e.nombre,
        e.IdTipoEstado
    from estado e
    where e.IdTipoEstado = IdTipoEstado;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Pedido`;
DELIMITER ;;
CREATE PROCEDURE `SP_Pedido`(
In idpedido int)
BEGIN
    select di.IdTipoDocIdentidad as TipoDocumento, di.numero, p.IdDireccionEnvio,
    com.IdComprobante, p.IdEstado, per.nombres, per.telefono, p.observaciones
    from pedido p inner join cliente cli on p.IdCliente = cli.IdCliente
    inner join persona per on cli.IdPersona = per.IdPersona
    inner join documentoIdentidad di on p.IdDocumentoIdentidad = di.IdDocumentoIdentidad 
    inner join comprobante com on p.IdPedido = com.IdPedido
    where p.IdPedido = idpedido;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoDetalle`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoDetalle`(
In iddetallepedido int
)
BEGIN
    select piz.IdPizza, piz.nombre, tam.nombre as tamanho, dp.cantidad, piz.precio
    from detallepedido dp inner join pizza piz on dp.IdPizza = piz.IdPizza
    inner join tamanho tam on piz.IdTamanho = tam.IdTamanho
    where dp.IdDetallePedido = iddetallepedido;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoDetalleElimina`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoDetalleElimina`(
In iddetallepedido int)
BEGIN
    delete from detallepedido where IdDetallePedido = iddetallepedido;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoDetalleInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoDetalleInserta`(
In cantidad int,
In idpedido int,
In idpizza int ,
Out iddetallepedido int)
BEGIN
    insert into detallepedido (cantidad, IdPizza, IdPedido)
    values (cantidad, idpedido, idpizza);
    
    SET iddetallepedido = LAST_INSERT_ID();
    
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoDetalleLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoDetalleLista`(
In idpedido int)
BEGIN
    select piz.IdPizza, piz.nombre, tam.nombre as tamanho, dp.cantidad, piz.precio
    from detallepedido dp inner join pizza piz on dp.IdPizza = piz.IdPizza
    inner join tamanho tam on piz.IdTamanho = tam.IdTamanho
    where dp.IdPedido = idpedido;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoDetalleModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoDetalleModifica`(
In cantidad int,
In iddetallepedido int)
BEGIN
    update detallepedido set cantidad = cantidad 
    where IdDetallePedido = iddetallepedido; 

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoInsertar`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoInsertar`(
In fechacreacion date,
In horacreacion time,
In iddireccionenvio int,
In idestado int,
In idcliente int,
In observaciones varchar(50),
Out idpedido int,
Out numero int
)
BEGIN
    declare cod_pedido int;
    declare cod_numero int;
    
    insert into pedido (fechaCreacion, horaCreacion, IdDireccionEnvio, IdEstado, IdCliente, observaciones)
    values (fechacreacion, horacreacion, iddireccionenvio, idestado, idcliente, observaciones);
    
    SET idpedido = LAST_INSERT_ID();
    
    select numero into numero from pedido where IdPedido = idpedido;    

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoLista`(
IN IdTipoDocIdentidad int,
In numero int
)
BEGIN
select p.idpedido, pi.nombre, p.horacreacion, d.Direccion, e.estadopedido
FROM pedido p inner join detallepedido dp on p.IdPedido = dp.IdPedido
inner join pizza pi on dp.idpizza = pi.idpizza
inner join direccion d on p.IdDireccionEnvio = d.IdDireccion
inner join estado e on p.IdEstado = e.IdEstado
inner join cliente c on p.IdCliente = c.IdCliente
inner join persona per on c.IdPersona = per.IdPersona
inner join documentoIdentidad di on per.IdDocumentoIdentidad = di.IdDocumentoIdentidad
where di.IdTipoDocIdentidad = IdTipoDocIdentidad and di.numero = numero;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoListaXClienteListoEntrega`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoListaXClienteListoEntrega`(
IN IdCliente int
)
BEGIN
select p.idpedido, p.numero, pi.nombre, p.fechacreacion, p.horacreacion, d.Direccion, e.estadopedido, p.observaciones
FROM pedido p inner join detallepedido dp on p.IdPedido = dp.IdPedido
inner join pizza pi on dp.idpizza = pi.idpizza
inner join direccion d on p.IdDireccionEnvio = d.IdDireccion
inner join estado e on p.IdEstado = e.IdEstado
where p.IdCliente = IdCliente and p.IdEstado = 1; -- asignar el codigo del item "listo"

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PedidoModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoModifica`(
In idpedido int,
In iddireccionenvio int,
In idestado int,
In idcliente int,
In idtipocomprobante int,
In observaciones varchar(50),
In telefono varchar(10)
)
BEGIN
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
        update pedido set IdDireccionEnvio = iddireccionenvio, IdEstado = idestado, 
        observaciones = observaciones
        where IdPedido = idpedido;
        
        update comprobante set IdTipoComprobante = idtipocomprobante
        where IdPedido = idpedido;
        
        update persona per
            inner join cliente cli on per.IdPersona = cli.IdPersona
            inner join pedido ped on cli.IdCliente = ped.IdCliente
                set per.telefono = telefono             
                where ped.IdPedido = idpedido;
            
    COMMIT; 
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Persona`;
DELIMITER ;;
CREATE PROCEDURE `SP_Persona`(
In idpersona int
 )
BEGIN
select p.IdPersona, p.nombres, p.apellidos, tp.nombre as TipoPersona,  
di.numero, tdi.nombre as TipoDocumentoIdentidad, e.nombre as estado
from persona p inner join tipopersona tp on p.IdTipoPersona = tp.IdTipoPersona 
inner join DocumentoIdentidad di on p.IdDocumentoIdentidad = di.IdDocumentoIdentidad
inner join tipoDocumentoIdentidad pdi on di.IdTipoDocIdentidad = tdi.IdTipoDocIdentidad
inner join estado e on p.IdEstado = e.IdEstado
where p.IdPersona = idpersona;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PersonaInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_PersonaInserta`(
In nombres varchar(100),
In apellidos varchar(100),
In IdTipoPersona int,
In IdDocumentoIdentidad int,
In IdEstado int,
In telefono varchar(10)
 )
BEGIN
insert into persona (nombres, apellidos, IdTipoPersona, IdDocumentoIdentidad, IdEstado, telefono)
values (nombres, apellidos, IdTipoPersona, IdDocumentoIdentidad, IdEstado, telefono);
SELECT LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PersonaLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_PersonaLista`(
In IdTipoDocIdentidad int,
In numero int )
BEGIN
select p.IdPersona, p.nombres, p.apellidos, tp.nombre as TipoPersona, di.numero, 
tdi.nombre as DocumentoIdentidad, e.nombre as estado
from persona p inner join tipopersona tp on p.IdTipoPersona = tp.IdTipoPersona 
inner join documentoIdentidad di on p.IdDocumentoIdentidad = di.IdDocumentoIdentidad
inner join tipoDocumentoIdentidad tdi on di.IdTipoDocIdentidad = tdi.IdTipoDocIdentidad
inner join estado e on p.IdEstado = e.IdEstado
where di.IdTipoDocIdentidad = IdTipoDocIdentidad and di.numero = numero;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PersonaModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_PersonaModifica`(
In idPersona int,
In nombres varchar(100),
In apellidos varchar(100),
In IdTipoPersona int,
In IdDocumentoIdentidad int,
In IdEstado int,
In telefono varchar(10)
 )
BEGIN
update persona 
set nombres = nombres, apellidos = apellidos, IdTipoPersona = IdTipoPersona, 
IdDocumentoIdentidad = IdDocumentoIdentidad, IdEstado = IdEstado, telefono = telefono
where idPersona = idPersona;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PersonaUsuario`;
DELIMITER ;;
CREATE PROCEDURE `SP_PersonaUsuario`(
In idpersona int
 )
BEGIN
select p.IdPersona, p.nombres, p.apellidos, p.telefono, tdi.nombre as TipoDocumentoIdentidad, di.numero,
p.nombre as TipoPersona,  u.nombreUsuario as usuario, u.contrasenha, r.nombre as Rol
from persona p inner join tipopersona tp on p.IdTipoPersona = tp.IdTipoPersona 
inner join DocumentoIdentidad di on p.IdDocumentoIdentidad = di.IdDocumentoIdentidad
inner join tipoDocumentoIdentidad pdi on di.IdTipoDocIdentidad = tdi.IdTipoDocIdentidad
inner join estado e on p.IdEstado = e.IdEstado
inner join usuario u on p.IdPersona = u.IdPersona
inner join rol r on u.IdRol = r.IdRol
where p.IdPersona = idpersona;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PersonaUsuarioInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_PersonaUsuarioInserta`(
In nombres varchar(100),
In apellidos varchar(100),
In IdTipoPersona int,
In IdDocumentoIdentidad int,
In IdEstado int,
In telefono varchar(10),
In nombreUsuario varchar(50),
In contrasenha varchar(50),
In IdRol int
 )
BEGIN
    DECLARE last_insert_id INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;
        insert into persona (nombres, apellidos, IdTipoPersona, IdDocumentoIdentidad, IdEstado, telefono)
        values (nombres, apellidos, IdTipoPersona, IdDocumentoIdentidad, IdEstado, telefono);
        
        SET last_insert_id = LAST_INSERT_ID(); -- assignment
        
        insert into usuario(nombreUsuario, contrasenha, IdPersona, IdRol)
        values (nombreUsuario, contrasenha, last_insert_id, IdRol);   
    COMMIT;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PersonaUsuarioModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_PersonaUsuarioModifica`(
In IdPersona int,
In nombres varchar(100),
In apellidos varchar(100),
In IdTipoPersona int,
In IdDocumentoIdentidad int,
In IdEstado int,
In telefono varchar(10),
In IdUsuario int,
In nombreUsuario varchar(50),
In contrasenha varchar(50),
In IdRol int
 )
BEGIN
    DECLARE last_insert_id INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;
        update persona set nombres = nombres, apellidos = apellidos, IdTipoPersona = IdTipoPersona, 
        IdDocumentoIdentidad = IdDocumentoIdentidad, IdEstado = IdEstado, telefono = telefono
        where IdPersona = IdPersona;            
        
        update usuario 
        set nombreUsuario = nombreUsuario, contrasenha = contrasenha, IdPersona = IdPersona, IdRol = IdRol
        where IdUsuario = IdUsuario;
        
    COMMIT;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Pizza`;
DELIMITER ;;
CREATE PROCEDURE `SP_Pizza`(
    IdPizza int
)
BEGIN
    select
        p.IdPizza,
        p.nombre,
        p.precio,
        p.IdTipoPizza,
        tp.nombre as tipoPizza,
        p.IdTamanho,
        t.nombre as tamanho,
        p.IdEstado,
        e.nombre as estado
    from pizza p
    join tipopizza tp on p.IdTipoPizza = tp.IdTipoPizza
    join tamanho t on p.IdTamanho = t.IdTamanho
    join estado e on p.IdEstado = e.nombre
    where p.IdPizza = IdPizza;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PizzaInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_PizzaInserta`(
    out IdPizza int,
    nombre varchar(50),
    precio decimal(19,4),
    IdTipoPizza int,
    IdTamanho int,
    IdEstado int
)
BEGIN
    insert into pizza(
        nombre,
        precio,
        IdTipoPizza,
        IdTamanho,
        IdEstado
    )
    value (
        nombre,
        precio,
        IdTipoPizza,
        IdTamanho,
        IdEstado
    );
    SET IdPizza = LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PizzaLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_PizzaLista`(
    IdTipoPizza int
)
BEGIN
    declare IdTipoPizza_ini int default 0;
    declare IdTipoPizza_fin int default 999999;
    if IdTipoPizza <> 0 then
        set IdTipoPizza_ini = IdTipoPizza;
        set IdTipoPizza_fin = IdTipoPizza;
    end if;

    select
        p.IdPizza,
        p.nombre,
        p.precio,
        p.IdTipoPizza,
        tp.nombre as tipoPizza,
        p.IdTamanho,
        t.nombre as tamanho,
        p.IdEstado,
        e.nombre as estado
    from pizza p
    join tipopizza tp on p.IdTipoPizza = tp.IdTipoPizza
    join tamanho t on p.IdTamanho = t.IdTamanho
    join estado e on p.IdEstado = e.nombre
    where p.IdTipoPizza between IdTipoPizza_ini and IdTipoPizza_fin;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PizzaListaxNombre`;
DELIMITER ;;
CREATE PROCEDURE `SP_PizzaListaxNombre`(
In nombrepizza varchar(50)
)
BEGIN
    select piz.IdPizza, piz.nombre, tam.nombre as tamanho, tam.cantidadPorciones as porciones, piz.precio
    from pizza piz inner join tamanho tam on piz.IdTamanho = tam.IdTamanho
    where piz.nombre = nombrepizza;

END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PizzaModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_PizzaModifica`(
    IdPizza int,
    nombre varchar(50),
    precio decimal(19,4),
    IdTipoPizza int,
    IdTamanho int,
    IdEstado int
)
BEGIN
    update pizza p
        set p.nombre = nombre,
        p.precio = precio,
        p.IdTipoPizza = IdTipoPizza,
        p.IdTamanho = IdTamanho,
        p.IdEstado = IdEstado
    where p.IdPizza = IdPizza;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_PizzasRpt`;
DELIMITER ;;
CREATE PROCEDURE `SP_PizzasRpt`(
    fechaCreacion_inicio date,
    fechaCreacion_fin date
)
BEGIN
    select
        tpz.nombre as tipoPizza,
        sum(dp.cantidad)
        -- falta monto
    from detallepedido dp
    join pedido p on dp.IdPedido = p.IdPedido
    join pizza pz on dp.IdPizza = pz.IdPizza
    join tipopizza tpz on pz.IdTipoPizza = tpz.IdTipoPizza
    where p.fechacreacion between fechaCreacion_inicio and fechaCreacion_fin
    group by tpz.IdTipoPizza, dp.cantidad;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Rol`;
DELIMITER ;;
CREATE PROCEDURE `SP_Rol`(
    IdRol int
)
BEGIN
    select 
        r.IdRol,
        r.nombre
    from roles r
    where r.IdRol = IdRol;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_RolAccesoEliminaInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_RolAccesoEliminaInserta`(
    IdRol int,
    idsformulario varchar(50)
)
BEGIN
    
    declare idx,prev_idx int;
    declare v_id varchar(10);

    delete from acceso a 
    where a.IdRol = IdRol;

    set idx := locate(',', idsformulario,1);
    set prev_idx := 1;

    WHILE idx > 0 DO
        set v_id := substr(idsformulario, prev_idx, idx - prev_idx);
        insert into acceso (IdRol,IdFormulario) values (IdRol,v_id);
        set prev_idx := idx+1;
        set idx := locate(',', idsformulario, prev_idx);
    END WHILE;

    set v_id := substr(idsformulario, prev_idx);
    insert into acceso (IdRol,IdFormulario) values (IdRol,v_id);
    
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_RolAccesoLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_RolAccesoLista`(
    IdRol int
)
BEGIN
    select
        a.IdAcceso,
        a.IdFormulario,
        f.nombre as Formulario
    from acceso a
    join formulario f on a.IdFormulario = f.IdFormulario
    join roles r on a.IdRol = r.IdRol
    where a.IdRol = IdRol;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_RolInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_RolInserta`(
    out IdRol int,
    nombre varchar(50)
)
BEGIN
    insert roles (
        nombre
    )
    value (
        nombre
    );
    SET IdRol = LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_RolLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_RolLista`()
BEGIN
    select 
        r.IdRol,
        r.nombre
    from roles r;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_RolModifca`;
DELIMITER ;;
CREATE PROCEDURE `SP_RolModifca`(
    IdRol int,
    nombre varchar(50)
)
BEGIN
    update roles r
        set r.nombre = nombre
    where r.IdRol = IdRol;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TamanhoPizza`;
DELIMITER ;;
CREATE PROCEDURE `SP_TamanhoPizza`(
    IdTamanho int
)
BEGIN
    select 
        t.IdTamanho,
        t.nombre,
        t.cantidadPorciones
    from tamanho t
    where t.IdTamanho = IdTamanho;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TamanhoPizzaInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_TamanhoPizzaInserta`(
    out IdTamanho int,
    nombre varchar(30),
    cantidadPorciones int
)
BEGIN
    insert into tamanho(
        nombre,
        cantidadPorciones
    )
    value (
        nombre,
        cantidadPorciones
    );
    SET IdTamanho = LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TamanhoPizzaLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_TamanhoPizzaLista`()
BEGIN
    select 
        t.IdTamanho,
        t.nombre,
        t.cantidadPorciones
    from tamanho t;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TamanhoPizzaModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_TamanhoPizzaModifica`(
    IdTamanho int,
    nombre varchar(30),
    cantidadPorciones int
)
BEGIN
    update tamanho t
        set t.nombre = nombre,
        t.cantidadPorciones = cantidadPorciones
    where t.IdTamanho = IdTamanho;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoDocumentoLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoDocumentoLista`()
BEGIN
    select
        tdi.IdTipoDocIdentidad,
        tdi.nombre,
        tdi.cantidadCaracteres
    from tipodocumentoidentidad tdi;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPersona`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPersona`(
    IdTipoPersona int
)
BEGIN
    select
        tp.IdTipoPersona,
        tp.nombre
    from tipopersona tp
    where tp.IdTipoPersona = IdTipoPersona;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPersonaInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPersonaInserta`(
    out IdTipoPersona int,
    nombre varchar(50)
)
BEGIN
    insert into tipopersona(
        nombre
    )
    value (
        nombre
    );
    SET IdTipoPersona = LAST_INSERT_ID();
END
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPersonaLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPersonaLista`()
BEGIN
    select
        tp.IdTipoPersona,
        tp.nombre
    from tipopersona tp;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPersonaModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPersonaModifica`(
    IdTipoPersona int,
    nombre varchar(50)
)
BEGIN
    update tipopersona tp
    set tp.nombre = nombre
    where tp.IdTipoPersona = IdTipoPersona;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPizza`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPizza`(
    IdTipoPizza int
)
BEGIN
    select 
        t.IdTipoPizza,
        t.nombre,
        t.descripcion
    from tipopizza t
    where t.IdTipoPizza = IdTipoPizza;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPizzaInserta`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPizzaInserta`(
    out IdTipoPizza int,
    nombre varchar(50),
    descripcion varchar(50)
)
BEGIN
    insert into tipopizza(
        nombre,
        descripcion
    )
    value (
        nombre,
        descripcion
    );
    SET IdTipoPizza = LAST_INSERT_ID();
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPizzaLista`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPizzaLista`()
BEGIN
    select 
        t.IdTipoPizza,
        t.nombre,
        t.descripcion
    from tipopizza t;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_TipoPizzaModifica`;
DELIMITER ;;
CREATE PROCEDURE `SP_TipoPizzaModifica`(
    IdTipoPizza int,
    nombre varchar(50),
    descripcion varchar(50)
)
BEGIN
    update tipopizza t
        set t.nombre = nombre,
        t.descripcion = descripcion
    where t.IdTipoPizza = IdTipoPizza;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_Usuario`;
DELIMITER ;;
CREATE PROCEDURE `SP_Usuario`(
    nombreUsuario varchar(50),
    contrasenha varchar(50)
)
BEGIN
    select 
        IdUsuario
    from usuario u
    join persona p
    on u.IdPersona = p.IdPersona
    where u.nombreUsuario = nombreUsuario
    and u.contrasenha = contrasenha
    and p.IdEstado = 6; -- activo
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `SP_VentasRpt`;
DELIMITER ;;
CREATE PROCEDURE `SP_VentasRpt`(
    fechaEmision_inicio date,
    fechaEmision_fin date
)
BEGIN
    select
        c.IdComprobante,
        c.numero,
        c.fechaEmision,
        c.horaEmision,
        c.IdTipoComprobante,
        t.nombre as tipoComprobante,
        c.IdPedido,
        c.IdEstado,
        e.nombre as estado
    from comprobante c
    join tipocomprobante t on c.IdTipoComprobante = t.IdTipoComprobante
    join estado e on c.IdEstado = e.IdEstado
    where c.fechaEmision between fechaEmision_inicio and fechaEmision_fin;
END ;;
DELIMITER ;
