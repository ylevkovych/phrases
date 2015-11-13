CREATE DATABASE phrases CHARACTER SET utf8 COLLATE utf8_general_ci;

-- GRANT ALL ON phrases .* TO 'ph_user' IDENTIFIED BY 'qa4rf67@$';
-- CREATE USER 'ph_user' IDENTIFIED BY 'qa4rf67@$';

USE phrases;

CREATE TABLE phrase (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    phrase TEXT NOT NULL DEFAULT '',
    transcription TEXT NOT NULL DEFAULT '',
    description TEXT NOT NULL DEFAULT '',
    created BIGINT(20) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC)
)ENGINE = InnoDB;
