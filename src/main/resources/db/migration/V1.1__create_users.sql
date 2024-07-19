create table public.users
(
    id              bigserial
        primary key,
    email           varchar      not null,
    password_digest varchar      not null,
    created_at      timestamp(6) not null,
    updated_at      timestamp(6) not null
);

create unique index index_users_on_email
    on public.users (email);
