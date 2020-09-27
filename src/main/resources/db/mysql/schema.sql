-- CREATE DATABASE IF NOT EXISTS petclinic;

-- ALTER DATABASE petclinic
--   DEFAULT CHARACTER SET utf8
--   DEFAULT COLLATE utf8_general_ci;

-- GRANT ALL PRIVILEGES ON petclinic.* TO user@localhost IDENTIFIED BY 'user';
use petclinic;

create table if not exists users
(
    id bigint not null auto_increment primary key,
    username varchar(255) null,
    password varchar(255) null
) engine = InnoDB;

create table if not exists roles
(
    id bigint not null auto_increment primary key,
    name enum ('ADMIN', 'EDITOR', 'USER', 'ANONYMOUS') not null
) engine = InnoDB;

create table if not exists user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK_user_role_users
        foreign key (user_id) references users (id),
    constraint FK_user_role_roles
        foreign key (role_id) references roles (id)
) engine = InnoDB;


create table if not exists owners
(
    id         bigint auto_increment primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    address    varchar(255) null,
    city       varchar(255) null,
    phone      varchar(255) null,
    index(last_name)
) engine = InnoDB;

create table if not exists pets
(
    id         bigint auto_increment primary key,
    birth_date date                                               null,
    kind       enum ('CAT', 'DOG', 'FISH', 'REPTILES', 'UNKNOWN') not null,
    name       varchar(255)                                       null,
    owner_id   bigint null,
    FOREIGN KEY (owner_id) REFERENCES owners(id)
) engine = InnoDB;

create table if not exists visits
(
    id          bigint auto_increment
        primary key,
    description varchar(255) null,
    pet_id      bigint       not null,
    visit_date  date         null,
    FOREIGN KEY (pet_id) REFERENCES pets(id)
) engine = InnoDB;
