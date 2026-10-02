create table if not exists orders
(
    id           uuid                    default gen_random_uuid() primary key,
    product_name varchar(255)   not null,
    price        decimal(12, 2) not null check ( price > 0 ),
    quantity     bigint         not null check ( quantity > 0 ),
    status       varchar(25)    not null default 'NEW',
    created_at   timestamptz    not null default now(),
    updated_at   timestamptz
);

-- пока индексы не нужны так как нет бизнеса задач, который бы требовал поиска строка по индексам