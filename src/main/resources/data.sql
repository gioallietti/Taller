INSERT IGNORE INTO `tipo_usuarios` (`id`, `nombre`)
VALUES  (1, 'Administrador'), (2, 'Técnico'), (3, 'Recepcionista') ;

INSERT IGNORE INTO usuarios (`id`, `tipo_usuario_id`, `apellido`, `cedula`, `email`, `nombre`, `password`, `telefono`, `activo`)
VALUES
(1, 1, 'Admin', '10000001', 'admin@taller.com', 'Admin', '1234', '099000001', 1);

INSERT IGNORE INTO estados (`id`, `nombre`)
VALUES
(1, 'Ingresado'),
(2, 'En Análisis'),
(3, 'Esperando repuesto'),
(4, 'En reparación'),
(5, 'Reparado'),
(6, 'Sin solución');

INSERT IGNORE INTO prioridades (`id`, `nivel`, `nombre`)
VALUES
(1, 1, 'Alta'),
(2, 2, 'Media'),
(3, 3, 'Baja');

INSERT IGNORE INTO paises (`id`, `nombre`, `activo`)
VALUES
(1, 'Uruguay', 1),
(2, 'Brasil', 1),
(3, 'Argentina', 1),
(4, 'Chile', 1),
(5, 'Paraguay', 1),
(6, 'Bolivia', 1),
(7, 'Perú', 1),
(8, 'Ecuador', 1),
(9, 'Colombia', 1),
(10, 'Venezuela', 1),
(11, 'México', 1),
(12, 'Guatemala', 1),
(13, 'Honduras', 1),
(14, 'El Salvador', 1),
(15, 'Nicaragua', 1),
(16, 'Costa Rica', 1),
(17, 'Panamá', 1),
(18, 'Cuba', 1),
(19, 'República Dominicana', 1),
(20, 'Puerto Rico', 1),
(21, 'España', 1),
(22, 'Portugal', 1),
(23, 'Francia', 1),
(24, 'Italia', 1),
(25, 'Alemania', 1),
(26, 'Suiza', 1),
(27, 'Austria', 1),
(28, 'Países Bajos', 1),
(29, 'Bélgica', 1),
(30, 'Suecia', 1),
(31, 'Noruega', 1),
(32, 'Dinamarca', 1),
(33, 'Finlandia', 1),
(34, 'Rusia', 1),
(35, 'Ucrania', 1),
(36, 'Polonia', 1),
(37, 'Hungría', 1),
(38, 'Grecia', 1),
(39, 'Turquía', 1),
(40, 'China', 1),
(41, 'Japón', 1),
(42, 'Corea del Sur', 1),
(43, 'India', 1),
(44, 'Australia', 1),
(45, 'Nueva Zelanda', 1),
(46, 'Sudáfrica', 1),
(47, 'Marruecos', 1),
(48, 'Egipto', 1),
(49, 'Arabia Saudita', 1),
(50, 'Israel', 1),
(51, 'Canadá', 1),
(52, 'Estados Unidos', 1);
