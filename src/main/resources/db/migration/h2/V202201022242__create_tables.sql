CREATE TABLE IF NOT EXISTS `roles`
(
    `id`          BIGINT                     NOT NULL AUTO_INCREMENT,
    `role_name`   VARCHAR(16)                NOT NULL,
    `role_status` ENUM ('ACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `users`
(
    `id`              BIGINT                     NOT NULL AUTO_INCREMENT,
    `first_name`      VARCHAR(64)                NOT NULL,
    `last_name`       VARCHAR(64)                NOT NULL,
    `birth_date`      DATETIME                   NULL,
    `email`           VARCHAR(32)                NOT NULL,
    `password`        VARCHAR(256)               NULL,
    `passport_number` VARCHAR(9)                 NULL,
    `address`         VARCHAR(64)                NULL,
    `user_status`     ENUM ('ACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    `role_id`         BIGINT                     NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT email_unique UNIQUE (`email`),
    CONSTRAINT `users_roles_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `roles` (`id`)
);

CREATE TABLE IF NOT EXISTS `genres`
(
    `id`           BIGINT                     NOT NULL AUTO_INCREMENT,
    `genre_name`   VARCHAR(256)               NOT NULL,
    `genre_status` ENUM ('ACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `authors`
(
    `id`            BIGINT                     NOT NULL AUTO_INCREMENT,
    `first_name`    VARCHAR(64)                NOT NULL,
    `last_name`     VARCHAR(64)                NOT NULL,
    `author_status` ENUM ('ACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `books`
(
    `id`              BIGINT                     NOT NULL AUTO_INCREMENT,
    `title`           VARCHAR(64)                NOT NULL,
    `release_year`    INT                        NOT NULL,
    `pages`           INT                        NOT NULL,
    `book_photo_path` VARCHAR(256)               NOT NULL,
    `book_status`     ENUM ('ACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `book_genre_links`
(
    `book_id`  BIGINT NOT NULL,
    `genre_id` BIGINT NOT NULL,
    CONSTRAINT `book_genre_link_book_fk`
        FOREIGN KEY (`book_id`)
            REFERENCES `books` (`id`),
    CONSTRAINT `book_genre_link_genres_fk`
        FOREIGN KEY (`genre_id`)
            REFERENCES `genres` (`id`)
);

CREATE TABLE IF NOT EXISTS `author_book_links`
(
    `author_id` BIGINT NOT NULL,
    `book_id`   BIGINT NOT NULL,
    CONSTRAINT `author_link_fk`
        FOREIGN KEY (`author_id`)
            REFERENCES `authors` (`id`),
    CONSTRAINT `book_link_fk`
        FOREIGN KEY (`book_id`)
            REFERENCES `books` (`id`)
);

CREATE TABLE IF NOT EXISTS `book_copies`
(
    `id`                 BIGINT                                    NOT NULL AUTO_INCREMENT,
    `price_per_day`      DECIMAL                                   NOT NULL,
    `register_date`      DATETIME                                  NOT NULL DEFAULT now(),
    `published_year`     INT                                       NULL,
    `copy_status`        ENUM ('AVAILABLE', 'ORDERED', 'DELETED' ) NOT NULL DEFAULT 'AVAILABLE',
    `book_id`            BIGINT                                    NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `copy_book_link_fk`
        FOREIGN KEY (`book_id`)
            REFERENCES `books` (`id`)
);

CREATE TABLE IF NOT EXISTS `orders`
(
    `id`              BIGINT                     NOT NULL AUTO_INCREMENT,
    `creation_date`   DATETIME                   NOT NULL DEFAULT now(),
    `expiration_date` DATETIME                   NOT NULL,
    `fine_sum`        DECIMAL                    NULL,
    `order_status`    ENUM ('ACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    `user_id`         BIGINT                     NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `user_order_link_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
);

CREATE TABLE IF NOT EXISTS `order_book_copy_links`
(
    `order_id`     BIGINT NOT NULL,
    `book_copy_id` BIGINT NOT NULL,
    CONSTRAINT `order_link_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `orders` (`id`),
    CONSTRAINT `book_copy_fk`
        FOREIGN KEY (`book_copy_id`)
            REFERENCES `book_copies` (`id`)
);
