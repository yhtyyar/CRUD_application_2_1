CREATE TABLE IF NOT EXISTS "writers"
(
    id         BIGSERIAL,
    first_name CHARACTER VARYING(20) NOT NULL,
    last_name  CHARACTER VARYING(20) NOT NULL,
    CONSTRAINT writers_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS "posts"
(
    id        BIGSERIAL,
    writer_id BIGINT                 NOT NULL,
    content   CHARACTER VARYING(200) NOT NULL DEFAULT 'Нет записи',
    created   DATE NOT NULL,
    updated   DATE NOT NULL,
    CONSTRAINT posts_pkey PRIMARY KEY (id),
    CONSTRAINT fk_writer_post FOREIGN KEY (writer_id)
    REFERENCES writers MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
    );

CREATE TABLE IF NOT EXISTS "regions"
(
    id          BIGSERIAL,
    writer_id   BIGINT                NOT NULL,
    region_name CHARACTER VARYING(20) NOT NULL DEFAULT 'Регион не указан',
    CONSTRAINT regions_pkey PRIMARY KEY (id),
    CONSTRAINT fk_writer_region FOREIGN KEY (writer_id)
    REFERENCES writers MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
    );