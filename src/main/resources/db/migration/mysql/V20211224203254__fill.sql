INSERT INTO `roles` (`role_name`)
    VALUES ('reader'),
           ('librarian'),
           ('admin');

INSERT INTO `users` (`last_name`, `first_name`, `birth_date`, `email`, `password`, `passport_number`, `address`,
                     `role_id`)
VALUES ('Явлаш', 'Лиза', '2000-08-28', 'yavlashliza@gmail.com',
        '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'LI1616318', 'Герасименко 3',
        2),
       ('Иванов', 'Пётр', '2001-01-01', 'ivanov@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'PO2451234', 'Володько 3', 3),
       ('Иванов', 'Влад', '2001-01-01', 'ivanov1@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'PI2451234', 'Володько 6', 1),
       ('Cидорова', 'Елена', '1999-08-20', 'elena@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'PI2479234', 'Некрасова 26',
        1),
       ('Петров', 'Иван', '2001-01-02', 'petrov@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'UY2479234', 'Ангарская 12',
        1),
       ('Смирнов', 'Александр', '1987-12-01', 'sasha87@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'UY2979234', 'Байкальская 45',
        1),
       ('Попов', 'Пётр', '2005-09-09', 'popov@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'BY2473234', 'Володько 3', 1),
       ('Котова', 'Ирина', '1978-08-15', 'kotova@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'BY1473634', 'Некрасова 26', 1),
       ('Васильев', 'Влад', '2002-01-02', 'vasiliev@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'BY2473235', 'Володько 6', 1),
       ('Соколова', 'Елена', '1998-08-21', 'sokolova@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'KH2493235', 'Некрасова 26',
        1),
       ('Михайлов', 'Иван', '2000-01-03', 'ivan@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'BT2493235', 'Ангарская 12',
        1),
       ('Горбачев', 'Игорь', '1989-10-01', 'igor@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'KH2494235', 'Байкальская 45',
        1),
       ('Борисов', 'Александр', '2001-01-01', 'sasha1@gmail.com', '$2a$10$y/tZj5T6gf7qpsbASq6Aq.LtXC1H.tIicrM1Vt6YSXguabMsPGRg2', 'KE1414235', 'Володько',
        1);

INSERT INTO `orders` (`creation_date`, `expiration_date`, `fine_sum`, `user_id`)
    VALUES ('2021-12-01', '2022-01-01', '20', 2),
           ('2021-12-20', '2022-02-14', '30', 4),
           ('2021-12-23', '2022-02-13', '10', 8),
           ('2021-12-15', '2022-02-12', '9', 3),
           ('2021-12-30', '2022-02-12', '38', 11),
           ('2021-12-01', '2022-02-11', '40', 7),
           ('2021-12-15', '2022-02-12', '9', 9),
           ('2021-12-30', '2022-02-12', '38', 10),
           ('2021-12-01', '2022-02-11', '40', 7),
           ('2021-12-01', '2022-01-01', '20', 12),
           ('2021-12-20', '2022-02-19', '30', 4),
           ('2021-12-23', '2022-02-13', '10', 5),
           ('2021-12-15', '2022-02-18', '9', 11),
           ('2021-12-30', '2022-02-20', '38', 8),
           ('2021-12-01', '2022-02-10', '40', 6),
           ('2021-12-23', '2022-02-01', '36', 3);

INSERT INTO `authors` (`first_name`, `last_name`)
    VALUES ('Антон', 'Чехов'),
           ('Михаил', 'Лермонтов'),
           ('Джоан', 'Роулинг'),
           ('F. Scott', 'Fitzgerald'),
           ('Herman', 'Melville'),
           ('William', 'Shakespeare');

INSERT INTO `books` (`title`, `release_year`, `pages`, `book_photo_path`)
    VALUES ('Каштанка', '1985', '20', '/images/books/chehov-kashtanka.jpg'),
           ('Сочинения. Том 1', '1988', '660', '/images/books/lermontov.jpg'),
           ('Великий Гэтсби', '2014', '365', '/images/books/The_Great_Gatsby.jpg'),
           ('Сочинения. Том 2', '1988', '660', '/images/books/lermontov.jpg'),
           ('Моби Дик', '2021', '456', '/images/books/moby-dick-118.jpg'),
           ('Гамлет', '1988', '660', '/images/books/hamlet.jpg'),
           ('Гарри Поттер и узник Азкабана', '2007', '672', '/images/books/harry-potter-3.jpg'),
           ('Гарри Поттер и Филосовский камень', '1998', '600', '/images/books/hp1.jpg'),
           ('Гарри Поттер и Тайная комната', '2005', '702', '/images/books/ph2.jpg'),
           ('Гарри Поттер и Кубок огня', '2000', '636', '/images/books/hp4.jpg'),
           ('Гарри Поттер и Орден Феникса', '2003', '702', '/images/books/hp5.jpg'),
           ('Гарри Поттер и Принц‑полукровка', '2007', '607', '/images/books/hp6.jpg'),
           ('Гарри Поттер и Дары Смерти', '2007', '671', '/images/books/hp7.jpg');

INSERT INTO `genres` (`genre_name`)
    VALUES ('Detective'),
           ('Fantasy'),
           ('Adventure'),
           ('Mystery'),
           ('Horror'),
           ('Romance'),
           ('Western'),
           ('Children'),
           ('Documentary');

INSERT INTO `book_copies` (`price_per_day`, `published_year`, `book_id`)
    VALUES ('2', '1998', '1'),
           ('1', '1998', '2'),
           ('3', '1998', '3'),
           ('2', '1998', '6'),
           ('1', '1998', '4'),
           ('1', '1998', '5'),
           ('6', '1998', '12'),
           ('1', '1998', '13'),
           ('6', '1998', '7'),
           ( '1', '1998', '13'),
           ('6', '1998', '7'),
           ('1', '1998', '8'),
           ('6', '1998', '9'),
           ('1', '1998', '10'),
           ('6', '1998', '11'),
           ('6', '1998', '12'),
           ('1', '1998', '13'),
           ('6', '1998', '11'),
           ('1', '1998', '6'),
           ('6', '1998', '5'),
           ('1', '1998', '10');

INSERT INTO `book_genre_links`(`book_id`, `genre_id`)
    VALUES ('1', '1'),
           ('2', '2'),
           ('3', '3'),
           ('4', '4'),
           ('5', '1'),
           ('6', '2'),
           ('7', '3'),
           ('4', '3'),
           ('5', '4'),
           ('6', '1'),
           ('8', '4'),
           ('9', '1'),
           ('10', '2'),
           ('11', '3'),
           ('12', '3'),
           ('13', '4'),
           ('7', '1');

INSERT INTO `author_book_links`(`author_id`, `book_id`)
    VALUES ('1', '1'),
           ('2', '2'),
           ('4', '3'),
           ('5', '5'),
           ('2', '4'),
           ('6', '6'),
           ('3', '7'),
           ('3', '8'),
           ('3', '9'),
           ('3', '10'),
           ('3', '11'),
           ('3', '12'),
           ('3', '13');

INSERT INTO `order_book_copy_links`(`order_id`, `book_copy_id`)
    VALUES ('1', '1'),
           ('1', '2'),
           ('1', '3'),
           ('4', '4'),
           ('5', '6'),
           ('6', '5'),
           ('7', '2'),
           ('8', '3');