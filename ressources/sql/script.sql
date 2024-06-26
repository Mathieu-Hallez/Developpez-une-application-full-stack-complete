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
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `TOPIC` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `description` TINYTEXT,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `POST` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `content` LONGTEXT,
    `topic_id` INT NOT NULL,
    `author_id` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `COMMENT` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `comment` VARCHAR(255),
    `post_id` INT NOT NULL,
    `author_id` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `SUBSCRIBE` (
    `subscriber_id` INT NOT NULL,
    `topic_id` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (subscriber_id, topic_id)
);

ALTER TABLE `POST` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPIC` (`id`);
ALTER TABLE `POST` ADD FOREIGN KEY (`author_id`) REFERENCES `USER` (`id`);
ALTER TABLE `COMMENT` ADD FOREIGN KEY (`post_id`) REFERENCES `POST` (`id`);
ALTER TABLE `COMMENT` ADD FOREIGN KEY (`author_id`) REFERENCES `USER` (`id`);
ALTER TABLE `SUBSCRIBE` ADD FOREIGN KEY (`subscriber_id`) REFERENCES `USER` (`id`);
ALTER TABLE `SUBSCRIBE` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPIC` (`id`);

INSERT INTO USER (email, password, username) VALUES ('test@test.com', '$2a$10$CCL8/7mMKD3zTk9p625Kzuf30Ykr7vWlKClsXW1Jb6x1v9AMtAVaK', 'test');
INSERT INTO TOPIC (title, description) VALUES ('Java Topic', 'Java language topic');
INSERT INTO TOPIC (title, description) VALUES ('C/C++ Topic', 'C/C++ language topic');
INSERT INTO POST (title, content, topic_id, author_id) VALUES ('Primary types', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget lobortis nibh. Pellentesque ac lorem mi. Donec id ullamcorper dolor. Quisque quis nisl et ligula faucibus elementum. Mauris nec mattis libero. Etiam quis lacus erat. Pellentesque felis ante, aliquam vehicula lobortis id, placerat a tortor. ', 1, 1);

