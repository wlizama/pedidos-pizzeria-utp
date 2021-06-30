CREATE DATABASE  IF NOT EXISTS `pedidos-pizzeria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pedidos-pizzeria`;
-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: pedidos-pizzeria
-- ------------------------------------------------------
-- Server version 8.0.25-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Acceso`
--

drop table if exists `acceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `acceso` (
  `IdAcceso` int NOT NULL AUTO_INCREMENT,
  `IdFormulario` int NOT NULL,
  `IdRol` int DEFAULT NULL,
  PRIMARY KEY (`IdAcceso`),
  KEY `Acceso_ibfk_2` (`IdRol`),
  CONSTRAINT `Acceso_ibfk_2` FOREIGN KEY (`IdRol`) references `roles` (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Acceso`
--

lock tables `acceso` WRITE;
/*!40000 ALTER TABLE `acceso` DISABLE KEYS */;
insert into `acceso` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1);
/*!40000 ALTER TABLE `acceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

drop table if exists `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `cliente` (
  `IdCliente` int NOT NULL AUTO_INCREMENT,
  `IdPersona` int NOT NULL,
  PRIMARY KEY (`IdCliente`),
  KEY `R_43` (`IdPersona`),
  CONSTRAINT `Cliente_ibfk_1` FOREIGN KEY (`IdPersona`) references `persona` (`IdPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

lock tables `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comprobante`
--

drop table if exists `comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `comprobante` (
  `IdComprobante` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `fechaEmision` date NOT NULL,
  `horaEmision` time NOT NULL,
  `IdTipoComprobante` int NOT NULL,
  `monto` decimal(19,4) NOT NULL,
  `IdPedido` int NOT NULL,
  `IdEstado` int DEFAULT NULL,
  PRIMARY KEY (`IdComprobante`),
  KEY `R_14` (`IdTipoComprobante`),
  KEY `R_25` (`IdPedido`),
  KEY `R_45` (`IdEstado`),
  CONSTRAINT `Comprobante_ibfk_1` FOREIGN KEY (`IdTipoComprobante`) references `tipocomprobante` (`IdTipoComprobante`),
  CONSTRAINT `Comprobante_ibfk_2` FOREIGN KEY (`IdPedido`) references `pedido` (`IdPedido`),
  CONSTRAINT `Comprobante_ibfk_3` FOREIGN KEY (`IdEstado`) references `estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comprobante`
--

lock tables `comprobante` WRITE;
/*!40000 ALTER TABLE `comprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DetalleEnvio`
--

drop table if exists `detalleenvio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `detalleenvio` (
  `IdDetalleEnvio` int NOT NULL AUTO_INCREMENT,
  `hora_fin` time NOT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `IdEnvio` int NOT NULL,
  `IdPedido` int NOT NULL,
  PRIMARY KEY (`IdDetalleEnvio`),
  KEY `R_22` (`IdEnvio`),
  KEY `R_23` (`IdPedido`),
  CONSTRAINT `DetalleEnvio_ibfk_1` FOREIGN KEY (`IdEnvio`) references `envio` (`IdEnvio`),
  CONSTRAINT `DetalleEnvio_ibfk_2` FOREIGN KEY (`IdPedido`) references `pedido` (`IdPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetalleEnvio`
--

lock tables `detalleenvio` WRITE;
/*!40000 ALTER TABLE `detalleenvio` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleenvio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DetallePedido`
--

drop table if exists `detallepedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `detallepedido` (
  `IdDetallePedido` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `IdPedido` int NOT NULL,
  `IdPizza` int NOT NULL,
  PRIMARY KEY (`IdDetallePedido`),
  KEY `R_11` (`IdPedido`),
  KEY `R_17` (`IdPizza`),
  CONSTRAINT `DetallePedido_ibfk_1` FOREIGN KEY (`IdPedido`) references `pedido` (`IdPedido`),
  CONSTRAINT `DetallePedido_ibfk_2` FOREIGN KEY (`IdPizza`) references `pizza` (`IdPizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetallePedido`
--

lock tables `detallepedido` WRITE;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Direccion`
--

drop table if exists `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `direccion` (
  `IdDireccion` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(100) NOT NULL,
  `referencia` varchar(150) DEFAULT NULL,
  `IdCliente` int NOT NULL,
  `IdDistrito` int NOT NULL,
  `principal` tinyint(1) NOT NULL,
  `IdEstado` int NOT NULL,
  PRIMARY KEY (`IdDireccion`),
  KEY `R_19` (`IdCliente`),
  KEY `R_20` (`IdDistrito`),
  KEY `R_40` (`IdEstado`),
  CONSTRAINT `Direccion_ibfk_1` FOREIGN KEY (`IdCliente`) references `cliente` (`IdCliente`),
  CONSTRAINT `Direccion_ibfk_2` FOREIGN KEY (`IdDistrito`) references `distrito` (`IdDistrito`),
  CONSTRAINT `Direccion_ibfk_3` FOREIGN KEY (`IdEstado`) references `estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Direccion`
--

lock tables `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Distrito`
--

drop table if exists `distrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `distrito` (
  `IdDistrito` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `cobertura` tinyint(1) NOT NULL,
  PRIMARY KEY (`IdDistrito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Distrito`
--

lock tables `distrito` WRITE;
/*!40000 ALTER TABLE `distrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `distrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DocumentoIdentidad`
--

drop table if exists `documentoidentidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `documentoidentidad` (
  `IdDocumentoIdentidad` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(15) NOT NULL,
  `IdTipoDocIdentidad` int NOT NULL,
  PRIMARY KEY (`IdDocumentoIdentidad`),
  KEY `R_29` (`IdTipoDocIdentidad`),
  CONSTRAINT `DocumentoIdentidad_ibfk_1` FOREIGN KEY (`IdTipoDocIdentidad`) references `tipodocumentoidentidad` (`IdTipoDocIdentidad`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DocumentoIdentidad`
--

lock tables `documentoidentidad` WRITE;
/*!40000 ALTER TABLE `documentoidentidad` DISABLE KEYS */;
insert into `documentoidentidad` VALUES (1,'XXXXXXXXXXXXXXX',6);
/*!40000 ALTER TABLE `documentoidentidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Envio`
--

drop table if exists `envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `envio` (
  `IdEnvio` int NOT NULL AUTO_INCREMENT,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `IdPersona` int NOT NULL,
  `IdEstado` int NOT NULL,
  `numero` int NOT NULL,
  PRIMARY KEY (`IdEnvio`),
  KEY `R_21` (`IdPersona`),
  KEY `R_35` (`IdEstado`),
  CONSTRAINT `Envio_ibfk_1` FOREIGN KEY (`IdPersona`) references `persona` (`IdPersona`),
  CONSTRAINT `Envio_ibfk_2` FOREIGN KEY (`IdEstado`) references `estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Envio`
--

lock tables `envio` WRITE;
/*!40000 ALTER TABLE `envio` DISABLE KEYS */;
/*!40000 ALTER TABLE `envio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Estado`
--

drop table if exists `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `estado` (
  `IdEstado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `IdTipoEstado` int DEFAULT NULL,
  PRIMARY KEY (`IdEstado`),
  KEY `fk_Estado_1_idx` (`IdTipoEstado`),
  CONSTRAINT `fk_Estado_1` FOREIGN KEY (`IdTipoEstado`) references `tipoestado` (`IdTipoEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estado`
--

lock tables `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
insert into `estado` VALUES (1,'Generado',1),(2,'Listo para entrega',1),(3,'En Camino',1),(4,'Finalizado',1),(5,'Finalizado con incidencias',1),(6,'Activo',2),(7,'Inactivo',2),(8,'Pendiente',3),(9,'Finalizado',3),(10,'Anulado',3),(11,'Disponible',4),(12,'No Disponible',4);
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Formulario`
--

drop table if exists `formulario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `formulario` (
  `IdFormulario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`IdFormulario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formulario`
--

lock tables `formulario` WRITE;
/*!40000 ALTER TABLE `formulario` DISABLE KEYS */;
insert into `formulario` VALUES (1,'Menu Pedidos'),(2,'Menu Delivery'),(3,'Menu Mantenimiento'),(4,'Menu Reportes'),(5,'Menu Mi sessión');
/*!40000 ALTER TABLE `formulario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pedido`
--

drop table if exists `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `pedido` (
  `IdPedido` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `fechacreacion` date NOT NULL,
  `horacreacion` time NOT NULL,
  `IdDireccionEnvio` int NOT NULL,
  `IdEstado` int NOT NULL,
  `IdCliente` int NOT NULL,
  `observaciones` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdPedido`),
  KEY `R_15` (`IdEstado`),
  KEY `R_26` (`IdCliente`),
  CONSTRAINT `Pedido_ibfk_1` FOREIGN KEY (`IdEstado`) references `estado` (`IdEstado`),
  CONSTRAINT `Pedido_ibfk_2` FOREIGN KEY (`IdCliente`) references `cliente` (`IdCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pedido`
--

lock tables `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Persona`
--

drop table if exists `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `persona` (
  `IdPersona` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `IdTipoPersona` int NOT NULL,
  `IdDocumentoIdentidad` int NOT NULL,
  `IdEstado` int DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`IdPersona`),
  KEY `R_18` (`IdTipoPersona`),
  KEY `R_30` (`IdDocumentoIdentidad`),
  KEY `R_36` (`IdEstado`),
  CONSTRAINT `Persona_ibfk_1` FOREIGN KEY (`IdTipoPersona`) references `tipopersona` (`IdTipoPersona`),
  CONSTRAINT `Persona_ibfk_2` FOREIGN KEY (`IdDocumentoIdentidad`) references `documentoidentidad` (`IdDocumentoIdentidad`),
  CONSTRAINT `Persona_ibfk_3` FOREIGN KEY (`IdEstado`) references `estado` (`IdEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Persona`
--

lock tables `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
insert into `persona` VALUES (1,'Super Usuario de Sistema',NULL,5,1,6,NULL);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pizza`
--

drop table if exists `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `pizza` (
  `IdPizza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(19,4) NOT NULL,
  `IdTipoPizza` int NOT NULL,
  `IdTamanho` int NOT NULL,
  `IdEstado` int DEFAULT NULL,
  PRIMARY KEY (`IdPizza`),
  KEY `R_12` (`IdTipoPizza`),
  KEY `R_44` (`IdEstado`),
  KEY `Pizza_ibfk_2` (`IdTamanho`),
  CONSTRAINT `Pizza_ibfk_1` FOREIGN KEY (`IdTipoPizza`) references `tipopizza` (`IdTipoPizza`),
  CONSTRAINT `Pizza_ibfk_2` FOREIGN KEY (`IdTamanho`) references `tamanho` (`IdTamanho`),
  CONSTRAINT `Pizza_ibfk_3` FOREIGN KEY (`IdEstado`) references `estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pizza`
--

lock tables `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

drop table if exists `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `roles` (
  `IdRol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

lock tables `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
insert into `roles` VALUES (1,'Super usuario'),(2,'Cajero'),(3,'Administrador');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tamanho`
--

drop table if exists `tamanho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `tamanho` (
  `IdTamanho` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `cantidadPorciones` int NOT NULL,
  PRIMARY KEY (`IdTamanho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tamanho`
--

lock tables `tamanho` WRITE;
/*!40000 ALTER TABLE `tamanho` DISABLE KEYS */;
/*!40000 ALTER TABLE `tamanho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoComprobante`
--

drop table if exists `tipocomprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `tipocomprobante` (
  `IdTipoComprobante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `IdEstado` int NOT NULL,
  PRIMARY KEY (`IdTipoComprobante`),
  KEY `R_39` (`IdEstado`),
  CONSTRAINT `TipoComprobante_ibfk_1` FOREIGN KEY (`IdEstado`) references `estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoComprobante`
--

lock tables `tipocomprobante` WRITE;
/*!40000 ALTER TABLE `tipocomprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipocomprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoDocumentoIdentidad`
--

drop table if exists `tipodocumentoidentidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `tipodocumentoidentidad` (
  `IdTipoDocIdentidad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `cantidadCaracteres` int DEFAULT NULL,
  PRIMARY KEY (`IdTipoDocIdentidad`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoDocumentoIdentidad`
--

lock tables `tipodocumentoidentidad` WRITE;
/*!40000 ALTER TABLE `tipodocumentoidentidad` DISABLE KEYS */;
insert into `tipodocumentoidentidad` VALUES (1,'L.E / DNI',8),(2,'CARNET EXT.',12),(3,'RUC',11),(4,'PASAPORTE',12),(5,'PART. DE NACIMIENTO-IDENTIDAD',15),(6,'OTROS',15);
/*!40000 ALTER TABLE `tipodocumentoidentidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoEstado`
--

drop table if exists `tipoestado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `tipoestado` (
  `IdTipoEstado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`IdTipoEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoEstado`
--

lock tables `tipoestado` WRITE;
/*!40000 ALTER TABLE `tipoestado` DISABLE KEYS */;
insert into `tipoestado` VALUES (1,'Pedido / Envio'),(2,'Usuario'),(3,'Comprobante'),(4,'Pizza');
/*!40000 ALTER TABLE `tipoestado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoPersona`
--

drop table if exists `tipopersona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `tipopersona` (
  `IdTipoPersona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`IdTipoPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoPersona`
--

lock tables `tipopersona` WRITE;
/*!40000 ALTER TABLE `tipopersona` DISABLE KEYS */;
insert into `tipopersona` VALUES (1,'Gerencia'),(2,'Cajero'),(3,'Delivery'),(4,'Cliente'),(5,'Otros');
/*!40000 ALTER TABLE `tipopersona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoPizza`
--

drop table if exists `tipopizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `tipopizza` (
  `IdTipoPizza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`IdTipoPizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoPizza`
--

lock tables `tipopizza` WRITE;
/*!40000 ALTER TABLE `tipopizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipopizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

drop table if exists `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create table `usuario` (
  `IdUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) NOT NULL,
  `contrasenha` varchar(50) NOT NULL,
  `IdPersona` int NOT NULL,
  `IdRol` int NOT NULL,
  PRIMARY KEY (`IdUsuario`),
  KEY `R_32` (`IdPersona`),
  KEY `R_34` (`IdRol`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`IdPersona`) references `persona` (`IdPersona`),
  CONSTRAINT `Usuario_ibfk_2` FOREIGN KEY (`IdRol`) references `roles` (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

lock tables `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
insert into `usuario` VALUES (1,'suuser','7c4a8d09ca3762af61e59520943dc26494f8941b',1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-24 22:51:25
