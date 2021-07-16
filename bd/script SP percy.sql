

--
-- Dumping routines for database 'pedidos-pizzeria'
--
-- !50003 DROP PROCEDURE IF EXISTS `SP_Cliente` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClienteDireccion` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClienteDireccionInserta` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClienteDireccionLista` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `SP_ClienteDireccionLista`(
In idcliente int)
BEGIN
	select idDireccion, direccion, referencia, principal
    from direccion
    where IdCliente = idcliente;

END ;;
DELIMITER ;
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClienteDireccionModifica` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClienteInserta` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClienteLista` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClienteModifica` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ClientexTipoDocNumero` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ComprobanteInsertar` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_ComprobanteModifica` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_Envio` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_EnvioDetalle` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_EnvioDetalleEliminar` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioDetalleEliminar`(
In iddetalleenvio int)
BEGIN
	delete from detalleenvio where IdDetalleEnvio = iddetalleenvio;

END ;;
DELIMITER ;
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_EnvioDetalleInserta` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_EnvioDetalleLista` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_EnvioInsertar` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_EnvioLista` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `SP_EnvioLista`(
In estado int)
BEGIN
select e.IdEnvio, e.horainicio, e.horafin, e.IdEstado, est.nombre as estado
from envio e inner join estado est on e.IdEstado = est.IdEstado
where IdEstado = estado;

END ;;
DELIMITER ;
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_EnvioModificar` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_Pedido` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoDetalle` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoDetalleElimina` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoDetalleElimina`(
In iddetallepedido int)
BEGIN
	delete from detallepedido where IdDetallePedido = iddetallepedido;
END ;;
DELIMITER ;
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoDetalleInserta` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoDetalleLista` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoDetalleModifica` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE PROCEDURE `SP_PedidoDetalleModifica`(
In cantidad int,
In iddetallepedido int)
BEGIN
	update detallepedido set cantidad = cantidad 
    where IdDetallePedido = iddetallepedido; 

END ;;
DELIMITER ;
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoInsertar` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoLista` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoListaXClienteListoEntrega` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PedidoModifica` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_Persona` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PersonaInserta` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PersonaLista` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PersonaModifica` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PersonaUsuario` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PersonaUsuarioInserta` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PersonaUsuarioModifica` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !50003 DROP PROCEDURE IF EXISTS `SP_PizzaListaxNombre` */;
-- !50003 SET @saved_cs_client      = @@character_set_client */ ;
-- !50003 SET @saved_cs_results     = @@character_set_results */ ;
-- !50003 SET @saved_col_connection = @@collation_connection */ ;
-- !50003 SET character_set_client  = utf8mb4 */ ;
-- !50003 SET character_set_results = utf8mb4 */ ;
-- !50003 SET collation_connection  = utf8mb4_general_ci */ ;
-- !50003 SET @saved_sql_mode       = @@sql_mode */ ;
-- !50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
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
-- !50003 SET sql_mode              = @saved_sql_mode */ ;
-- !50003 SET character_set_client  = @saved_cs_client */ ;
-- !50003 SET character_set_results = @saved_cs_results */ ;
-- !50003 SET collation_connection  = @saved_col_connection */ ;
-- !40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- !40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- !40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- !40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- !40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- !40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- !40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- !40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-04 21:09:48
