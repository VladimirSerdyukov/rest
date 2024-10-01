create table if not exists "Quotes" (
    id serial primary key,
    "text" text not null,
    "quoteId" int4 not null
);

create table if not exists "Chats" (
    id serial primary key,
    "chatId" int8 not null,
    "lastId" int not null
);

truncate table public."Quotes" continue identity restrict;
drop table "Quotes";