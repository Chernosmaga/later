drop table if exists users, items, tags, item_notes cascade;

create table if not exists users
(
    id bigint generated always as identity primary key,
    email varchar(320),
    first_name varchar(100),
    last_name varchar(100),
    registration_date timestamp,
    date_of_birth timestamp,
    state varchar(50)
);

create table if not exists items
(
    id bigint generated always as identity primary key,
    user_id bigint,
    url varchar(1000),
    resolved_url varchar(1000),
    mime_type varchar(100),
    title varchar(255),
    has_image boolean,
    has_video boolean,
    unread boolean,
    date_resolved timestamp without time zone,
    constraint fk_items_to_users foreign key (user_id) references users(id),
    unique (user_id, resolved_url)
);

create table if not exists tags
(
    id bigint generated always as identity primary key,
    item_id bigint,
    name varchar(50),
    constraint fk_tags_to_items foreign key (item_id) references items(id)
);

create table if not exists item_notes
(
    id bigint generated always as identity primary key,
    item_id bigint,
    text varchar(1000),
    note_date timestamp,
    constraint items foreign key (item_id) references items(id)
);