CREATE TABLE `depot` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `latitude` REAL NOT NULL,
  `longitude` REAL NOT NULL,
  `created_on` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp
);

CREATE TABLE `order` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `depot_id` INTEGER NOT NULL,
  `created_on` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp
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
create table order_SEQ (next_val bigint);
insert into order_SEQ values ( 1 );
create table package_SEQ (next_val bigint);
insert into package_SEQ values ( 1 );


INSERT INTO depot (name, latitude, longitude) VALUES ('depot-test-1', 45.0, 55.0);
INSERT INTO depot (name, latitude, longitude) VALUES ('depot-test-2', 55.0, 65.0);
INSERT INTO depot (name, latitude, longitude) VALUES ('depot-test-3', 65.0, 75.0);




