select * from pedido;
select * from cliente;
select * from direccion;
select * from distrito;
select * from tipocomprobante;
select * from comprobante;
select * from estado;
insert into tipocomprobante values (null,'factura', 6);
insert into distrito values (null,'Pueblo Libre', 1);
insert into direccion values (null,'Mayta Capac 1','cerca al Kiosko',4,4,1,6);
insert into pedido values (null, 5, '2021-07-11',"19:05:00",5,6,4,'');
insert into comprobante values (null, 4, '2021-07-10','14:55:00',3,35.0,5,6)