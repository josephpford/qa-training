drop database if exists todos;
create database todos;

use todos;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    `password` varchar(50) not null,
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
    due_date date null,
    is_completed bit not null default 0,
    constraint fk_todo_app_user_id
        foreign key (app_user_id)
        references app_user(app_user_id)
);

insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

insert into app_user (username, `password`, disabled)
    values
    ('john@smith.com', 'password', 0),
    ('sally@jones.com', 'password', 0);

insert into app_user_role
    values
    (1, 2),
    (2, 1);

insert into todo (app_user_id, `description`, due_date)
	values
    (1, 'Buy milk.', null),
    (1, 'Walk the dog.', null),
    (1, 'Wash the car.', null),
    (1, 'Workout.', null),
    (1, 'Make dinner reservations.', null),
    (1, 'Save money for vacation.', '2022-12-01'),
    (2, 'Cook dinner.', null),
    (2, 'Pack a lunch.', null),
    (2, 'Give the dog a bath.', null),
    (2, 'Change the oil.', null),
    (2, 'Get cash from the ATM.', null);
