drop database if exists todos;
create database todos;

use todos;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    disabled boolean not null default(0)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

create table todo (
    todo_id int primary key auto_increment,
    app_user_id int not null,
    `description` varchar(200) not null,
    constraint fk_todo_app_user_id
        foreign key (app_user_id)
        references app_user(app_user_id)
);

insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

-- passwords are set to "P@ssw0rd!"
insert into app_user (username, password_hash, disabled)
    values
    ('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

insert into app_user_role
    values
    (1, 2),
    (2, 1);

insert into todo (app_user_id, `description`)
	values
    (1, 'Buy milk.'),
    (1, 'Walk the dog.'),
    (1, 'Wash the car.'),
    (1, 'Workout.'),
    (1, 'Make dinner reservations.'),
    (2, 'Cook dinner.'),
    (2, 'Pack a lunch.'),
    (2, 'Give the dog a bath.'),
    (2, 'Change the oil.'),
    (2, 'Get cash from the ATM.');
