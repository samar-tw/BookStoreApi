DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `authors`;
DROP TABLE IF EXISTS `book_author`;

CREATE TABLE `books` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `status` varchar(255),
  `price` int,
  `published_year` int,
  `category` ENUM (
  	'Science fiction',
  	'Drama',
  	'Manga',
  	'Action and Adventure',
  	'Romance',
  	'Mystery',
  	'Horror',
  	'Health',
  	'Guide',
  	'Diaries',
  	'Comics',
  	'Journals',
  	'Biographies',
  	'Fantasy',
  	'Science',
  	'Art',
  	'Other'),
  `description` varchar(255),
  `created_at` datetime,
  `updated_at` datetime
);


CREATE TABLE `authors` (
                           `id` int PRIMARY KEY AUTO_INCREMENT,
                           `first_name` varchar(255) NOT NULL,
                           `last_name` varchar(255),
                           `birth_date` date,
                           `created_at` datetime,
                           `updated_at` datetime
);

CREATE TABLE `book_author` (
                               `id` int PRIMARY KEY AUTO_INCREMENT,
                               `book_id` int,
                               `author_id` int
);


ALTER TABLE `book_author` ADD FOREIGN KEY (`book_id`) REFERENCES `books` (`id`);

ALTER TABLE `book_author` ADD FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);



INSERT INTO `books` VALUES
(1, 'Magna a neque', NULL, 4656, '2002', 'Action and Adventure', 'Vestibulum ut eros non enim commodo hendrerit. Donec porttitor tellus', '2019-06-29 14:09:18', '2019-05-12 09:52:17'),
(2, 'Nulla aliquet', NULL, 3807, '1967', 'Mystery', 'Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum', '2019-09-11 20:17:47', '2020-07-10 11:56:59'),
(3, 'Sed consequat auctor', NULL, 8855, '1993', 'Science', 'Proin eget odio. Aliquam vulputate ullamcorper magna. Sed eu eros. Nam consequat dolor vitae dolor.', '2019-06-10 02:51:59', '2019-07-28 13:27:49'),
(4, 'Quam vel sapien imperdiet', NULL, 3367, '2011', 'Science fiction', 'Nulla aliquet. Proin velit. Sed malesuada augue ut lacus. Nulla tincidunt, neque', '2020-08-25 23:32:18', '2020-09-19 09:05:03');

INSERT INTO `authors` VALUES
(1, 'Erica', 'French', '1951-10-15', '2019-09-23 13:30:52', '2019-04-18 19:22:38'),
(2, 'Karleigh', 'Donovan', '1945-01-23', '2019-10-17 23:54:56', '2019-09-08 07:47:09'),
(3, 'Germane', 'Mckee', '1960-04-23', '2020-08-07 14:12:01', NULL),
(4, 'Kylee', 'Diaz', '1977-09-19', '2019-02-02 05:55:11', NULL),
(5, 'Juliet', 'Hart', '1955-02-02', '2019-02-02 05:55:11', '2020-05-06 13:29:29'),
(6, 'Larissa', 'Mcguire', '1959-06-23', '2020-08-15 05:40:28', '2018-12-24 23:19:05'),
(7, 'Joy', 'Simmons', '1975-05-18', '2019-02-02 05:55:11', '2020-05-06 13:29:29'),
(8, 'Vladimir', 'Steele', '1981-12-01', '2019-09-09 08:46:06', '2020-07-20 05:27:55');

INSERT INTO `book_author` VALUES
(1, 1, 1),
(2, 1, 3),
(3, 1, 4),
(4, 2, 2),
(5, 2, 5),
(6, 3, 7),
(7, 3, 8),
(8, 4, 6);
