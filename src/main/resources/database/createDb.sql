create table users(
	username varchar(50) not null primary key,
	password varchar(255) not null,
	enabled boolean not null);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

insert into users(username,password,enabled) values ('dbuser','{noop}simplon', true);
insert into users(username,password,enabled) values ('dbdevelopper','{noop}simplon', true);
insert into users(username,password,enabled) values ('dbmanager','{noop}simplon', true);
insert into users(username,password,enabled) values ('dbadmin','{bcrypt}$2a$10$OhwFVfhBW0Rv2TUtS4UFSOtvMFbGnPPEFkFcKnXif9bBAfWFnKm16', true);

INSERT INTO authorities (username, authority)VALUES ('dbuser', 'USER');
INSERT INTO authorities (username, authority)VALUES ('dbdevelopper', 'DEVELOPPER');
INSERT INTO authorities (username, authority)VALUES ('dbmanager', 'MANAGER');
INSERT INTO authorities (username, authority)VALUES ('dbadmin', 'ADMIN');
