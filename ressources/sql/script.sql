DROP TABLE `SUBSCRIBE`;
DROP TABLE `COMMENT`;
DROP TABLE `POST`;
DROP TABLE `TOPIC`;
DROP TABLE `USER`;

CREATE TABLE IF NOT EXISTS `USER` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `username` varchar(50) NOT NULL,
    `create_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `TOPIC` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `description` TINYTEXT,
    `create_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `POST` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `content` LONGTEXT,
    `topic_id` INT NOT NULL,
    `author_id` INT NOT NULL,
    `create_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `COMMENT` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `comment` VARCHAR(255),
    `post_id` INT NOT NULL,
    `author_id` INT NOT NULL,
    `create_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `SUBSCRIBE` (
    `subscriber_id` INT NOT NULL,
    `topic_id` INT NOT NULL,
    `create_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE `POST` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPIC` (`id`);
ALTER TABLE `POST` ADD FOREIGN KEY (`author_id`) REFERENCES `USER` (`id`);
ALTER TABLE `COMMENT` ADD FOREIGN KEY (`post_id`) REFERENCES `POST` (`id`);
ALTER TABLE `COMMENT` ADD FOREIGN KEY (`author_id`) REFERENCES `USER` (`id`);
ALTER TABLE `SUBSCRIBE` ADD FOREIGN KEY (`subscriber_id`) REFERENCES `USER` (`id`);
ALTER TABLE `SUBSCRIBE` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPIC` (`id`);

INSERT INTO USER (email, password, username, create_at, update_at) VALUES ('test@test.com', 'test!1234', 'test', now(), now());
INSERT INTO TOPIC (title, description, create_at, update_at) VALUES ('Java Topic', 'Java language topic', now(), now());
INSERT INTO POST (title, content, topic_id, author_id, create_at, update_at) VALUES ('Primary types', 'Lorem lipsu...', 1, 1, now(), now());

