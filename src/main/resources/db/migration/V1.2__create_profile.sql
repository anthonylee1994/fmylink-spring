create table if not exists public.profiles
(
    id         bigserial
        primary key,
    user_id    bigint       not null,
    avatar_url varchar,
    username   varchar      not null,
    created_at timestamp(6) not null,
    updated_at timestamp(6) not null
);

create index if not exists index_profiles_on_user_id
    on public.profiles (user_id);

create unique index if not exists index_profiles_on_username
    on public.profiles (username);

