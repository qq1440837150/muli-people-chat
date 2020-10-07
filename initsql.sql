drop table if exists group_info;
create table group_info(
    id INTEGER primary key auto_increment,
    group_id INTEGER,
    password Integer,
    is_password BOOLEAN,
    unique (group_id)
);
INSERT INTO group_info(group_id, password, is_password) VALUE (1234,1234,true);
INSERT INTO group_info(group_id, password, is_password) VALUE (1235,1234,true);

drop table if exists user_info;
create table user_info(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id integer unique ,
    user_name varchar(50),
    password integer,
    picture varchar(100)
);
insert into user_info(user_id, user_name, password, picture) VALUE (1234,'zhf',1234,'picture');
drop table if exists group_user_relative;
create table group_user_relative(
    id integer primary key auto_increment,
    group_id integer,
    user_id integer,
    power integer,
    foreign key (group_id) references group_info(group_id),
    foreign key (user_id) references user_info(user_id),
    unique (group_id,user_id)
);
insert into group_user_relative(group_id, user_id) VALUE (1234,1234);
insert into group_user_relative(group_id, user_id) VALUE (1235,1234);