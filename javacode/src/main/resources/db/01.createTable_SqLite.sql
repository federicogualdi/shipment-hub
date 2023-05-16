CREATE TABLE `depot` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `latitude` REAL NOT NULL,
  `longitude` REAL NOT NULL,
  `created_on` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp
);

CREATE TABLE `supplier` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `created_on` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp
);

CREATE TABLE `order` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `depot_id` INTEGER NOT NULL,
  `supplier_id` INTEGER NOT NULL,
  `created_on` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp,
  FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
);

CREATE TABLE `package` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `order_id` INTEGER NOT NULL,
  `latitude` REAL NOT NULL,
  `longitude` REAL NOT NULL,
  `status` tinyint NOT NULL,
  `created_on` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp,
  FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
);

create table depot_SEQ (next_val bigint);
insert into depot_SEQ values ( 1 );
create table supplier_SEQ (next_val bigint);
insert into supplier_SEQ values ( 1 );
create table order_SEQ (next_val bigint);
insert into order_SEQ values ( 1 );
create table package_SEQ (next_val bigint);
insert into package_SEQ values ( 1 );


INSERT INTO depot (name, latitude, longitude) VALUES ('Milano', 45.4639627, 9.189885);
INSERT INTO depot (name, latitude, longitude) VALUES ('Torino', 45.0684587, 7.6748147);
INSERT INTO depot (name, latitude, longitude) VALUES ('Roma', 41.8992204, 12.5090232);

INSERT INTO supplier (name) VALUES ('supplier-1');




