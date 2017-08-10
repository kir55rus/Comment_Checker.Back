create table comment
(
	id bigserial not null
		constraint comment_pkey
			primary key,
	comment_date timestamp not null,
	comment_text varchar(255) not null,
	url_id bigint not null
)
;

create table comment_check
(
	id bigserial not null
		constraint comment_check_pkey
			primary key,
	check_time timestamp not null,
	percent integer not null,
	result varchar(255) not null,
	comment_id bigint not null
		constraint fknm7dm2us5lj6l2cuycs81a543
			references comment
)
;

create table queries_urls
(
	id bigserial not null
		constraint queries_urls_pkey
			primary key,
	position integer not null,
	search_engine varchar(255) not null,
	weight bigint not null,
	search_query_id bigint not null,
	url_id bigint not null
)
;

create table query_group
(
	id serial not null
		constraint query_group_pkey
			primary key,
	name varchar(200) not null
)
;

create table search_query
(
	id bigserial not null
		constraint search_query_pkey
			primary key,
	frequency bigint not null
		constraint search_query_frequency_check
			check (frequency >= 0),
	google_checked boolean not null,
	search_query varchar(250) not null,
	yandex_checked boolean not null,
	group_id integer not null
		constraint fkd8pe38r434xaj7pm0ee7a2oh4
			references query_group
)
;

alter table queries_urls
	add constraint fko930l0xq0vxk214r62nabewxt
		foreign key (search_query_id) references search_query
;

create table settings
(
	id serial not null
		constraint settings_pkey
			primary key,
	name varchar(250),
	value varchar(255)
)
;

create table url
(
	id bigserial not null
		constraint url_pkey
			primary key,
	comment_block varchar(255) not null,
	url_date timestamp not null,
	url varchar(255) not null
)
;

alter table comment
	add constraint fk1irorcm67gg4ymnqnk8hjn7wq
		foreign key (url_id) references url
;

alter table queries_urls
	add constraint fkhn298dapgsnmq28bom5bajlg7
		foreign key (url_id) references url
;

