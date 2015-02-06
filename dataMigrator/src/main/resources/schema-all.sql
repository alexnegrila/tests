DROP TABLE DATA IF EXISTS;

CREATE TABLE DATA
(
   ID int PRIMARY KEY not null,
   ARTICLEID int,
   ATTRIBUTE varchar(20),
   VALUE varchar(32000),
   LANGUAGE smallint,
   TYPE smallint
);
