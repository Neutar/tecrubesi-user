create table neutar_user_detail (
	id uuid PRIMARY KEY,
	bio varchar(255),
	points int8,
	profile_picture_url varchar(255));

create table neutar_user (id uuid PRIMARY KEY,
	email varchar(255) UNIQUE,
	name varchar(255),
	password varchar(255),
	surname varchar(255),
	username varchar(255) UNIQUE,
	neutar_user_detail_id uuid references neutar_user_detail(id));

create table user_detail_badge (
	neutar_user_detail_id uuid not null references neutar_user_detail(id),
	badge varchar(255));