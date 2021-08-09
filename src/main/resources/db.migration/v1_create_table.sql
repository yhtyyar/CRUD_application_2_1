CREATE TABLE IF NOT EXISTS "writers"
(
    id         BIGSERIAL,
    first_name CHARACTER VARYING(50) NOT NULL,
    last_name  CHARACTER VARYING(50) NOT NULL,
    region_id  BIGINT,
    CONSTRAINT writers_pkey PRIMARY KEY (id),
    CONSTRAINT fk_writer_region FOREIGN KEY (region_id)
    REFERENCES regions MATCH FULL
    );

CREATE TABLE IF NOT EXISTS "posts"
(
    id        BIGSERIAL,
    writer_id BIGINT                 NOT NULL,
    content   CHARACTER VARYING(260) NOT NULL DEFAULT 'Нет записи',
    created   TIMESTAMP,
    updated   TIMESTAMP,
    CONSTRAINT posts_pkey PRIMARY KEY (id),
    CONSTRAINT fk_writer_post FOREIGN KEY (writer_id)
    REFERENCES writers MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
    );

CREATE TABLE IF NOT EXISTS "regions"
(
    id          BIGSERIAL,
    region_name CHARACTER VARYING(50) NOT NULL DEFAULT 'Регион не указан' UNIQUE,
    CONSTRAINT regions_pkey PRIMARY KEY (id)
    );