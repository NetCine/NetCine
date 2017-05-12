-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 11-05-2017 a las 09:18:03
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `bbddcine`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `CodCliente` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `Nombre` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `Apellidos` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `DNI` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `Telefono` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `Correo` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `NumTarjeta` int(11) NOT NULL,
  `Descuento` int(11) NOT NULL,
  `Clave` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`CodCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`CodCliente`, `Nombre`, `Apellidos`, `DNI`, `Telefono`, `Correo`, `FechaNacimiento`, `NumTarjeta`, `Descuento`, `Clave`) VALUES
('CE0001', 'Mario', 'Cliente Especial', '7854123-C', '547896321', 'Cliente@especial.com', '1990-05-01', 2147483647, 30, 'a1234567'),
('CN0001', 'Paco', 'Ejemplo Normal', '5151514-S', '587142958', 'Ejemplo@normal.com', '1998-06-04', 2147483647, 0, 'a1234567');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE IF NOT EXISTS `compras` (
  `CodCompra` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `CodCliente` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `CodSesion` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `NumEntradas` int(11) NOT NULL,
  `PrecioEntradas` double NOT NULL,
  PRIMARY KEY (`CodCompra`),
  KEY `CodCliente` (`CodCliente`),
  KEY `CodSesion` (`CodSesion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`CodCompra`, `CodCliente`, `CodSesion`, `NumEntradas`, `PrecioEntradas`) VALUES
('CA0001', 'CE0001', 'SN0001', 2, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE IF NOT EXISTS `empleado` (
  `CodEmple` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `Nombre` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `Apellidos` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `DNI` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `Telefono` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `Correo` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Clave` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Jornadas` date NOT NULL,
  `CodNomina` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `Funcion` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `DescripcionPuesto` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`DNI`),
  UNIQUE KEY `Correo` (`Correo`),
  KEY `CodNomina` (`CodNomina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`CodEmple`, `Nombre`, `Apellidos`, `DNI`, `Telefono`, `Correo`, `Clave`, `Jornadas`, `CodNomina`, `Funcion`, `DescripcionPuesto`) VALUES
('JF0001', 'Pascualo', 'Ejemplo Jefe', '0101010-A', '654987126', 'ejemplo@ejemplo.com', 'a1234567', '2010-00-20', '', 'Jefe de cine', 'Se encarga de organizar el cine'),
('EM0001', 'Pedro', 'Prueba', '0202022-B', '687412304', 'prueba@prueba.com', 'a1234567.', '2017-05-01', '', 'Encargado de las palomitas', 'Se encarga de hacer las palomitas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nomina`
--

CREATE TABLE IF NOT EXISTS `nomina` (
  `Cantidad` double NOT NULL,
  `DNI` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `CodNomina` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`CodNomina`),
  KEY `DNI` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `nomina`
--

INSERT INTO `nomina` (`Cantidad`, `DNI`, `CodNomina`) VALUES
(3000, '0101010-A', 'NO0001');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE IF NOT EXISTS `pelicula` (
  `Nombre` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `CodPelicula` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `TotalVendidas` int(11) NOT NULL,
  PRIMARY KEY (`CodPelicula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`Nombre`, `CodPelicula`, `TotalVendidas`) VALUES
('Star Wars', 'PL0001', 2000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peticion`
--

CREATE TABLE IF NOT EXISTS `peticion` (
  `CodPeticion` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `MotivoPeticion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Estado` tinyint(1) NOT NULL,
  `Revisado` tinyint(1) NOT NULL,
  `DNIEmple` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`CodPeticion`),
  KEY `DNIEmple` (`DNIEmple`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `peticion`
--

INSERT INTO `peticion` (`CodPeticion`, `MotivoPeticion`, `Estado`, `Revisado`, `DNIEmple`) VALUES
('PE0001', 'Cambio de horario', 1, 0, '0202022-B');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesion`
--

CREATE TABLE IF NOT EXISTS `sesion` (
  `CodSesion` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `NumSala` int(11) NOT NULL,
  `Hora` time NOT NULL,
  `numButacasTotal` int(11) NOT NULL,
  `CodPelicula` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`CodSesion`),
  KEY `CodPelicula` (`CodPelicula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `sesion`
--

INSERT INTO `sesion` (`CodSesion`, `NumSala`, `Hora`, `numButacasTotal`, `CodPelicula`) VALUES
('SN0001', 1, '00:20:17', 200, 'PL0001');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `CodUsuario` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `Correo` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Clave` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`CodUsuario`),
  UNIQUE KEY `Correo` (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`CodUsuario`, `Correo`, `Clave`) VALUES
('CE0001', 'Cliente@especial.com', 'a1234567'),
('CN0001', 'Ejemplo@normal.com', 'a1234567'),
('EM0001', 'prueba@prueba.com', 'a1234567.'),
('JF0001', 'ejemplo@ejemplo.com', 'a1234567');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`CodCliente`) REFERENCES `cliente` (`CodCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`CodSesion`) REFERENCES `sesion` (`CodSesion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD CONSTRAINT `nomina_ibfk_1` FOREIGN KEY (`DNI`) REFERENCES `empleado` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `peticion`
--
ALTER TABLE `peticion`
  ADD CONSTRAINT `peticion_ibfk_1` FOREIGN KEY (`DNIEmple`) REFERENCES `empleado` (`DNI`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sesion`
--
ALTER TABLE `sesion`
  ADD CONSTRAINT `sesion_ibfk_1` FOREIGN KEY (`CodPelicula`) REFERENCES `pelicula` (`CodPelicula`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
