CREATE DATABASE  IF NOT EXISTS `pedidos-pizzeria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pedidos-pizzeria`;
-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: pedidos-pizzeria
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

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

DROP TABLE IF EXISTS `Acceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Acceso` (
  `IdAcceso` int NOT NULL AUTO_INCREMENT,
  `IdFormulario` int NOT NULL,
  `IdRol` int DEFAULT NULL,
  PRIMARY KEY (`IdAcceso`),
  KEY `Acceso_ibfk_2` (`IdRol`),
  CONSTRAINT `Acceso_ibfk_2` FOREIGN KEY (`IdRol`) REFERENCES `Roles` (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Acceso`
--

LOCK TABLES `Acceso` WRITE;
/*!40000 ALTER TABLE `Acceso` DISABLE KEYS */;
INSERT INTO `Acceso` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1);
/*!40000 ALTER TABLE `Acceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `IdCliente` int NOT NULL AUTO_INCREMENT,
  `IdPersona` int NOT NULL,
  PRIMARY KEY (`IdCliente`),
  KEY `R_43` (`IdPersona`),
  CONSTRAINT `Cliente_ibfk_1` FOREIGN KEY (`IdPersona`) REFERENCES `Persona` (`IdPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comprobante`
--

DROP TABLE IF EXISTS `Comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Comprobante` (
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
  CONSTRAINT `Comprobante_ibfk_1` FOREIGN KEY (`IdTipoComprobante`) REFERENCES `TipoComprobante` (`IdTipoComprobante`),
  CONSTRAINT `Comprobante_ibfk_2` FOREIGN KEY (`IdPedido`) REFERENCES `Pedido` (`IdPedido`),
  CONSTRAINT `Comprobante_ibfk_3` FOREIGN KEY (`IdEstado`) REFERENCES `Estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comprobante`
--

LOCK TABLES `Comprobante` WRITE;
/*!40000 ALTER TABLE `Comprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DetalleEnvio`
--

DROP TABLE IF EXISTS `DetalleEnvio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DetalleEnvio` (
  `IdDetalleEnvio` int NOT NULL AUTO_INCREMENT,
  `hora_fin` time NOT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `IdEnvio` int NOT NULL,
  `IdPedido` int NOT NULL,
  PRIMARY KEY (`IdDetalleEnvio`),
  KEY `R_22` (`IdEnvio`),
  KEY `R_23` (`IdPedido`),
  CONSTRAINT `DetalleEnvio_ibfk_1` FOREIGN KEY (`IdEnvio`) REFERENCES `Envio` (`IdEnvio`),
  CONSTRAINT `DetalleEnvio_ibfk_2` FOREIGN KEY (`IdPedido`) REFERENCES `Pedido` (`IdPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetalleEnvio`
--

LOCK TABLES `DetalleEnvio` WRITE;
/*!40000 ALTER TABLE `DetalleEnvio` DISABLE KEYS */;
/*!40000 ALTER TABLE `DetalleEnvio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DetallePedido`
--

DROP TABLE IF EXISTS `DetallePedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DetallePedido` (
  `IdDetallePedido` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `IdPedido` int NOT NULL,
  `IdPizza` int NOT NULL,
  PRIMARY KEY (`IdDetallePedido`),
  KEY `R_11` (`IdPedido`),
  KEY `R_17` (`IdPizza`),
  CONSTRAINT `DetallePedido_ibfk_1` FOREIGN KEY (`IdPedido`) REFERENCES `Pedido` (`IdPedido`),
  CONSTRAINT `DetallePedido_ibfk_2` FOREIGN KEY (`IdPizza`) REFERENCES `Pizza` (`IdPizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetallePedido`
--

LOCK TABLES `DetallePedido` WRITE;
/*!40000 ALTER TABLE `DetallePedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `DetallePedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Direccion`
--

DROP TABLE IF EXISTS `Direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Direccion` (
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
  CONSTRAINT `Direccion_ibfk_1` FOREIGN KEY (`IdCliente`) REFERENCES `Cliente` (`IdCliente`),
  CONSTRAINT `Direccion_ibfk_2` FOREIGN KEY (`IdDistrito`) REFERENCES `Distrito` (`IdDistrito`),
  CONSTRAINT `Direccion_ibfk_3` FOREIGN KEY (`IdEstado`) REFERENCES `Estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Direccion`
--

LOCK TABLES `Direccion` WRITE;
/*!40000 ALTER TABLE `Direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Distrito`
--

DROP TABLE IF EXISTS `Distrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Distrito` (
  `IdDistrito` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `cobertura` tinyint(1) NOT NULL,
  PRIMARY KEY (`IdDistrito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Distrito`
--

LOCK TABLES `Distrito` WRITE;
/*!40000 ALTER TABLE `Distrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `Distrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DocumentoIdentidad`
--

DROP TABLE IF EXISTS `DocumentoIdentidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DocumentoIdentidad` (
  `IdDocumentoIdentidad` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(15) NOT NULL,
  `IdTipoDocIdentidad` int NOT NULL,
  PRIMARY KEY (`IdDocumentoIdentidad`),
  KEY `R_29` (`IdTipoDocIdentidad`),
  CONSTRAINT `DocumentoIdentidad_ibfk_1` FOREIGN KEY (`IdTipoDocIdentidad`) REFERENCES `TipoDocumentoIdentidad` (`IdTipoDocIdentidad`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DocumentoIdentidad`
--

LOCK TABLES `DocumentoIdentidad` WRITE;
/*!40000 ALTER TABLE `DocumentoIdentidad` DISABLE KEYS */;
INSERT INTO `DocumentoIdentidad` VALUES (1,'XXXXXXXXXXXXXXX',6);
/*!40000 ALTER TABLE `DocumentoIdentidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Envio`
--

DROP TABLE IF EXISTS `Envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Envio` (
  `IdEnvio` int NOT NULL AUTO_INCREMENT,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `IdPersona` int NOT NULL,
  `IdEstado` int NOT NULL,
  `numero` int NOT NULL,
  PRIMARY KEY (`IdEnvio`),
  KEY `R_21` (`IdPersona`),
  KEY `R_35` (`IdEstado`),
  CONSTRAINT `Envio_ibfk_1` FOREIGN KEY (`IdPersona`) REFERENCES `Persona` (`IdPersona`),
  CONSTRAINT `Envio_ibfk_2` FOREIGN KEY (`IdEstado`) REFERENCES `Estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Envio`
--

LOCK TABLES `Envio` WRITE;
/*!40000 ALTER TABLE `Envio` DISABLE KEYS */;
/*!40000 ALTER TABLE `Envio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Estado`
--

DROP TABLE IF EXISTS `Estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Estado` (
  `IdEstado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `IdTipoEstado` int DEFAULT NULL,
  PRIMARY KEY (`IdEstado`),
  KEY `fk_Estado_1_idx` (`IdTipoEstado`),
  CONSTRAINT `fk_Estado_1` FOREIGN KEY (`IdTipoEstado`) REFERENCES `TipoEstado` (`IdTipoEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estado`
--

LOCK TABLES `Estado` WRITE;
/*!40000 ALTER TABLE `Estado` DISABLE KEYS */;
INSERT INTO `Estado` VALUES (1,'Generado',1),(2,'Listo para entrega',1),(3,'En Camino',1),(4,'Finalizado',1),(5,'Finalizado con incidencias',1),(6,'Activo',2),(7,'Inactivo',2),(8,'Pendiente',3),(9,'Finalizado',3),(10,'Anulado',3),(11,'Disponible',4),(12,'No Disponible',4);
/*!40000 ALTER TABLE `Estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Formulario`
--

DROP TABLE IF EXISTS `Formulario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Formulario` (
  `IdFormulario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`IdFormulario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formulario`
--

LOCK TABLES `Formulario` WRITE;
/*!40000 ALTER TABLE `Formulario` DISABLE KEYS */;
INSERT INTO `Formulario` VALUES (1,'Menu Pedidos'),(2,'Menu Delivery'),(3,'Menu Mantenimiento'),(4,'Menu Reportes'),(5,'Menu Mi sessión');
/*!40000 ALTER TABLE `Formulario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pedido`
--

DROP TABLE IF EXISTS `Pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pedido` (
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
  CONSTRAINT `Pedido_ibfk_1` FOREIGN KEY (`IdEstado`) REFERENCES `Estado` (`IdEstado`),
  CONSTRAINT `Pedido_ibfk_2` FOREIGN KEY (`IdCliente`) REFERENCES `Cliente` (`IdCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pedido`
--

LOCK TABLES `Pedido` WRITE;
/*!40000 ALTER TABLE `Pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Persona`
--

DROP TABLE IF EXISTS `Persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Persona` (
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
  CONSTRAINT `Persona_ibfk_1` FOREIGN KEY (`IdTipoPersona`) REFERENCES `TipoPersona` (`IdTipoPersona`),
  CONSTRAINT `Persona_ibfk_2` FOREIGN KEY (`IdDocumentoIdentidad`) REFERENCES `DocumentoIdentidad` (`IdDocumentoIdentidad`),
  CONSTRAINT `Persona_ibfk_3` FOREIGN KEY (`IdEstado`) REFERENCES `Estado` (`IdEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Persona`
--

LOCK TABLES `Persona` WRITE;
/*!40000 ALTER TABLE `Persona` DISABLE KEYS */;
INSERT INTO `Persona` VALUES (1,'Super Usuario de Sistema',NULL,5,1,6,NULL);
/*!40000 ALTER TABLE `Persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pizza`
--

DROP TABLE IF EXISTS `Pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pizza` (
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
  CONSTRAINT `Pizza_ibfk_1` FOREIGN KEY (`IdTipoPizza`) REFERENCES `TipoPizza` (`IdTipoPizza`),
  CONSTRAINT `Pizza_ibfk_2` FOREIGN KEY (`IdTamanho`) REFERENCES `Tamanho` (`IdTamanho`),
  CONSTRAINT `Pizza_ibfk_3` FOREIGN KEY (`IdEstado`) REFERENCES `Estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pizza`
--

LOCK TABLES `Pizza` WRITE;
/*!40000 ALTER TABLE `Pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Roles` (
  `IdRol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES (1,'Super usuario'),(2,'Cajero'),(3,'Administrador');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tamanho`
--

DROP TABLE IF EXISTS `Tamanho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tamanho` (
  `IdTamanho` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `cantidadPorciones` int NOT NULL,
  PRIMARY KEY (`IdTamanho`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tamanho`
--

LOCK TABLES `Tamanho` WRITE;
/*!40000 ALTER TABLE `Tamanho` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tamanho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoComprobante`
--

DROP TABLE IF EXISTS `TipoComprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TipoComprobante` (
  `IdTipoComprobante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `IdEstado` int NOT NULL,
  PRIMARY KEY (`IdTipoComprobante`),
  KEY `R_39` (`IdEstado`),
  CONSTRAINT `TipoComprobante_ibfk_1` FOREIGN KEY (`IdEstado`) REFERENCES `Estado` (`IdEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoComprobante`
--

LOCK TABLES `TipoComprobante` WRITE;
/*!40000 ALTER TABLE `TipoComprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `TipoComprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoDocumentoIdentidad`
--

DROP TABLE IF EXISTS `TipoDocumentoIdentidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TipoDocumentoIdentidad` (
  `IdTipoDocIdentidad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `cantidadCaracteres` int DEFAULT NULL,
  PRIMARY KEY (`IdTipoDocIdentidad`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoDocumentoIdentidad`
--

LOCK TABLES `TipoDocumentoIdentidad` WRITE;
/*!40000 ALTER TABLE `TipoDocumentoIdentidad` DISABLE KEYS */;
INSERT INTO `TipoDocumentoIdentidad` VALUES (1,'L.E / DNI',8),(2,'CARNET EXT.',12),(3,'RUC',11),(4,'PASAPORTE',12),(5,'PART. DE NACIMIENTO-IDENTIDAD',15),(6,'OTROS',15);
/*!40000 ALTER TABLE `TipoDocumentoIdentidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoEstado`
--

DROP TABLE IF EXISTS `TipoEstado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TipoEstado` (
  `IdTipoEstado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`IdTipoEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoEstado`
--

LOCK TABLES `TipoEstado` WRITE;
/*!40000 ALTER TABLE `TipoEstado` DISABLE KEYS */;
INSERT INTO `TipoEstado` VALUES (1,'Pedido / Envio'),(2,'Usuario'),(3,'Comprobante'),(4,'Pizza');
/*!40000 ALTER TABLE `TipoEstado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoPersona`
--

DROP TABLE IF EXISTS `TipoPersona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TipoPersona` (
  `IdTipoPersona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`IdTipoPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoPersona`
--

LOCK TABLES `TipoPersona` WRITE;
/*!40000 ALTER TABLE `TipoPersona` DISABLE KEYS */;
INSERT INTO `TipoPersona` VALUES (1,'Gerencia'),(2,'Cajero'),(3,'Delivery'),(4,'Cliente'),(5,'Otros');
/*!40000 ALTER TABLE `TipoPersona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoPizza`
--

DROP TABLE IF EXISTS `TipoPizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TipoPizza` (
  `IdTipoPizza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`IdTipoPizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoPizza`
--

LOCK TABLES `TipoPizza` WRITE;
/*!40000 ALTER TABLE `TipoPizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `TipoPizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuario` (
  `IdUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) NOT NULL,
  `contrasenha` varchar(50) NOT NULL,
  `IdPersona` int NOT NULL,
  `IdRol` int NOT NULL,
  PRIMARY KEY (`IdUsuario`),
  KEY `R_32` (`IdPersona`),
  KEY `R_34` (`IdRol`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`IdPersona`) REFERENCES `Persona` (`IdPersona`),
  CONSTRAINT `Usuario_ibfk_2` FOREIGN KEY (`IdRol`) REFERENCES `Roles` (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'suuser','7c4a8d09ca3762af61e59520943dc26494f8941b',1,1);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
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
