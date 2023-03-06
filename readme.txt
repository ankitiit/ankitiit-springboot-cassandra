cqlsh
describe keyspaces

CREATE KEYSPACE schoolkeyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

CREATE KEYSPACE ecommercekeyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

use schoolkeyspace;

CREATE TABLE Product(
   id int PRIMARY KEY,
   name text,
 );


 CREATE TABLE Teacher(
    id int PRIMARY KEY,
    email text,
    password text,
    firstName text,
    lastName text,
    highestQualification text,
    subjects text,
    experience int,
    contactNumber text,
    description text,
  );