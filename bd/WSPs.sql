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
END

--------------
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
END
------------
CREATE PROCEDURE `SP_TipoDocumentoLista`()
BEGIN
	select
		tdi.IdTipoDocIdentidad,
        tdi.nombre,
        tdi.cantidadCaracteres
    from tipodocumentoidentidad tdi;
END
----------------
CREATE  PROCEDURE `SP_Comprobante`(
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
END
---------------
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
END
-------------
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
END
-------------
CREATE PROCEDURE `SP_TipoPersonaLista`()
BEGIN
    select
        tp.IdTipoPersona,
        tp.nombre
    from tipopersona tp;
END
----------------------
CREATE PROCEDURE `SP_TipoPersona`(
    IdTipoPersona int
)
BEGIN
    select
        tp.IdTipoPersona,
        tp.nombre
    from tipopersona tp
    where tp.IdTipoPersona = IdTipoPersona;
END
----------------
CREATE PROCEDURE `SP_TipoPersonaInserta`(
    IdTipoPersona int,
    nombre varchar(50)
)
BEGIN
    insert into tipopersona(
        IdTipoPersona,
        nombre
    )
    value (
        IdTipoPersona,
        nombre
    );
END
-----------
CREATE PROCEDURE `SP_TipoPersonaModifica`(
    IdTipoPersona int,
    nombre varchar(50)
)
BEGIN
    update tipopersona tp
    set tp.nombre = nombre
    where tp.IdTipoPersona = IdTipoPersona;
END
---------------
CREATE PROCEDURE `SP_DistritoLista`()
BEGIN
    select 
        IdDistrito,
        nombre,
        cobertura
    from distrito d;
END
------------
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
END
-----------
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
END
-----------
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
END
---------
CREATE PROCEDURE `SP_TipoPizzaLista`()
BEGIN
    select 
        t.IdTipoPizza,
        t.nombre,
        t.descripcion
    from tipopizza t;
END
---------------
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
END
--------------
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
END
-------------------
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
END
------------------
CREATE PROCEDURE `SP_TamanhoPizzaLista`()
BEGIN
    select 
        t.IdTamanho,
        t.nombre,
        t.cantidadPorciones
    from tamanho t;
END
------------------
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
END
----------------
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
END
--------------
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
END
-----------------
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
END
---------------
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
END
------------
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
END
----------------
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
END
---------------
CREATE PROCEDURE `SP_RolLista`()
BEGIN
    select 
        r.IdRol,
        r.nombre
    from roles r;
END 
--------------
CREATE PROCEDURE `SP_Rol`(
    IdRol int
)
BEGIN
    select 
        r.IdRol,
        r.nombre
    from roles r
    where r.IdRol = IdRol;
END
-----------
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
END
--------------
CREATE PROCEDURE `SP_RolModifca`(
    IdRol int,
    nombre varchar(50)
)
BEGIN
    update roles r
        set r.nombre = nombre
    where r.IdRol = IdRol;
END
-------------
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
END
------------
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
    
END
-----------------
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
END
----------------------
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
END
------------------------
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
END