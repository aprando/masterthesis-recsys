CREATE DATABASE `recommender_schema` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `avaliacao_explicita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nivel` int(11) DEFAULT NULL,
  `data_avaliacao` date DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usu_id` (`usuario_id`),
  KEY `ite_id` (`item_id`),
  CONSTRAINT `avaliacao_explicita_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `avaliacao_explicita_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `avaliacao_implicita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nivel` int(11) DEFAULT NULL,
  `data_avaliacao` date DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usu_id` (`usuario_id`),
  KEY `ite_id` (`item_id`),
  CONSTRAINT `avaliacao_implicita_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `avaliacao_implicita_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `avaliacao_social` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) DEFAULT NULL,
  `descricao` varchar(40) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usu_id` (`usuario_id`),
  CONSTRAINT `avaliacao_social_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `avaliacao_social_possui_categorias` (
  `categoria_id` int(11) DEFAULT NULL,
  `avaliacao_social_id` int(11) DEFAULT NULL,
  KEY `categoria_id` (`categoria_id`),
  KEY `avaliacao_social_id` (`avaliacao_social_id`),
  CONSTRAINT `avaliacao_social_possui_categorias_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  CONSTRAINT `avaliacao_social_possui_categorias_ibfk_2` FOREIGN KEY (`avaliacao_social_id`) REFERENCES `Avaliacao_Social` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) DEFAULT NULL,
  `descricao` varchar(40) DEFAULT NULL,
  `categoria_pai_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_pai_id` (`categoria_pai_id`),
  CONSTRAINT `categoria_ibfk_1` FOREIGN KEY (`categoria_pai_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) DEFAULT NULL,
  `descricao` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `item_possui_categorias` (
  `categoria_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  KEY `categoria_id` (`categoria_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `item_possui_categorias_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  CONSTRAINT `item_possui_categorias_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sexo` char(1) DEFAULT NULL,
  `ocupacao` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `data_nascimento` DATETIME DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `facebook_id` varchar(255) DEFAULT NULL,
  `twitter_id` varchar(255) DEFAULT NULL,
  `googleplus_id` varchar(255) DEFAULT NULL,
  `instagram_id` varchar(255) DEFAULT NULL,
  `facebook_accesstoken` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

