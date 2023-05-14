CREATE TABLE `depot` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `latitude` REAL NOT NULL,
  `longitude` REAL NOT NULL,
  `created_on` TIMESTAMP not null,
  `updated_on` TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_on` TIMESTAMP not null,
  `updated_on` TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `package` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `latitude` REAL NOT NULL,
  `longitude` REAL NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created_on` TIMESTAMP not null,
  `updated_on` TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_package__package_ids` (`order_id`),
  CONSTRAINT `FK_package__order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
);

create sequence hibernate_sequence start with 1 increment by 1;