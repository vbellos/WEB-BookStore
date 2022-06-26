-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.20 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bookstore
CREATE DATABASE IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookstore`;

-- Dumping structure for table bookstore.admins
CREATE TABLE IF NOT EXISTS `admins` (
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.admins: ~0 rows (approximately)
DELETE FROM `admins`;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;

-- Dumping structure for table bookstore.authors
CREATE TABLE IF NOT EXISTS `authors` (
  `username` varchar(20) NOT NULL,
  `pen_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.authors: ~0 rows (approximately)
DELETE FROM `authors`;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` (`username`, `pen_name`) VALUES
	('jwhite', 'ΕΥΑΓΓΕΛΟΣ ΜΠΕΛΛΟΣ');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;

-- Dumping structure for table bookstore.books
CREATE TABLE IF NOT EXISTS `books` (
  `id` int NOT NULL DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `desc` text,
  `genre` varchar(45) DEFAULT NULL,
  `sold` int DEFAULT NULL,
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.books: ~5 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`id`, `name`, `author`, `desc`, `genre`, `sold`, `image`, `price`) VALUES
	(0, 'The sasdsfa', 'jwhite', 'sadsdasdg', 'Fiction', 7, '1345399360.jpg', 10.99),
	(14, 'test', 'leonard', 'test desc', 'horror', 8, '1.jpg', 10.5),
	(123, 'test', 'test authr', 'test desc', 'scifi', 10, '1.jpg', 10.5),
	(134514897, 'The sasdsfa', 'jwhite', 'sadsdasdg', 'Fiction', 3, '1345399360.jpg', 10.99),
	(1582504572, 'The Hobbit ', 'jwhite', 'The Hobbit, or There and Back Again is a children\'s fantasy novel by English author J. R. R. Tolkien. It was published on 21 September 1937 to wide critical acclaim, being nominated for the Carnegie Medal and awarded a prize from the New York Herald Tribune for best juvenile fiction. The book remains popular and is recognized as a classic in children\'s literature.', 'Fiction', 7, 'The_Hobbit_trilogy_dvd_cover.jpg', 11.99);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table bookstore.book_transaction
CREATE TABLE IF NOT EXISTS `book_transaction` (
  `trans_id` varchar(50) DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `datetime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.book_transaction: ~8 rows (approximately)
DELETE FROM `book_transaction`;
/*!40000 ALTER TABLE `book_transaction` DISABLE KEYS */;
INSERT INTO `book_transaction` (`trans_id`, `book_id`, `price`, `author`, `quantity`, `datetime`) VALUES
	('vbellos162229140557331.5', 123, 10.5, 'test authr', 3, '2021-06-04 10:07:03'),
	('vbellos162229419270010.5', 123, 10.5, 'test authr', 1, '2021-06-04 10:07:03'),
	('vbellos162229434547352.5', 14, 10.5, 'leonard', 5, '2021-06-04 10:07:04'),
	('vbellos162264146879232.97', 134514897, 10.99, 'jwhite', 3, NULL),
	('vbellos162280948196523.98', 1582504572, 11.99, 'jwhite', 2, '2021-06-04 15:24:42'),
	('vbellos1623073169355111.43', 0, 10.99, 'jwhite', 4, '2021-06-07 16:39:29'),
	('vbellos1623073169355111.43', 14, 10.5, 'leonard', 3, '2021-06-07 16:39:29'),
	('vbellos1623073169355111.43', 1582504572, 11.99, 'jwhite', 3, '2021-06-07 16:39:29'),
	('vbellos162523292932323.98', 1582504572, 11.99, 'jwhite', 2, '2021-07-02 16:35:29');
/*!40000 ALTER TABLE `book_transaction` ENABLE KEYS */;

-- Dumping structure for table bookstore.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `username` varchar(20) NOT NULL,
  `trans_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.clients: ~2 rows (approximately)
DELETE FROM `clients`;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`username`, `trans_id`) VALUES
	('gsmith', NULL),
	('vbellos', NULL);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;

-- Dumping structure for table bookstore.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `sender` varchar(45) DEFAULT NULL,
  `receiver` varchar(45) DEFAULT NULL,
  `text` text,
  `time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.messages: ~8 rows (approximately)
DELETE FROM `messages`;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` (`sender`, `receiver`, `text`, `time`) VALUES
	('gsmith', 'test', 'dssadsdasda', '2021-05-25 17:06:04'),
	('test', 'gsmith', 'gffdfg', '2021-05-25 17:06:07'),
	('gsmith', 'test', 'heyy', '2021-05-26 17:05:18'),
	('gsmith', 'vbellos', 'heyyy', '2021-05-26 17:05:33'),
	('vbellos', 'gsmith', 'hey g', '2021-05-26 17:05:49'),
	('gsmith', 'vbellos', 'how are you my man?', '2021-05-26 17:06:14'),
	('vbellos', 'gsmith', 'good', '2021-05-26 17:42:01'),
	('gsmith', 'vbellos', 'glad to hear that', '2021-05-26 17:42:26'),
	('vbellos', 'jwhite', 'heyyy', '2021-06-04 15:25:46'),
	('jwhite', 'vbellos', 'heyyy', '2021-06-04 15:26:52'),
	('vbellos', 'jwhite', 'se gamaw', '2021-07-02 16:35:09');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;

-- Dumping structure for table bookstore.reviews
CREATE TABLE IF NOT EXISTS `reviews` (
  `book_id` int NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `comment` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.reviews: ~3 rows (approximately)
DELETE FROM `reviews`;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` (`book_id`, `username`, `rating`, `comment`) VALUES
	(123, 'vbellos', 10, '    trsss'),
	(123, 'gsmith', 9, '    fdsfdsfdfdfds'),
	(123, 'vbellos', 7, '    dsdffsdfsdfd'),
	(1582504572, 'vbellos', 10, '    sdadsadassad');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;

-- Dumping structure for table bookstore.transactions
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `username` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.transactions: ~6 rows (approximately)
DELETE FROM `transactions`;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` (`id`, `username`, `price`, `date`) VALUES
	('vbellos162229134992231.5', 'vbellos', 31.5, '2021-05-29 15:29:10'),
	('vbellos162229140557331.5', 'vbellos', 31.5, '2021-05-29 15:30:06'),
	('vbellos162229419270010.5', 'vbellos', 10.5, '2021-05-29 16:16:33'),
	('vbellos162229434547352.5', 'vbellos', 52.5, '2021-05-29 16:19:05'),
	('vbellos162264146879232.97', 'vbellos', 32.97, '2021-06-02 16:44:29'),
	('vbellos162280948196523.98', 'vbellos', 23.98, '2021-06-04 15:24:42'),
	('vbellos1623073169355111.43', 'vbellos', 111.43, '2021-06-07 16:39:29'),
	('vbellos162523292932323.98', 'vbellos', 23.98, '2021-07-02 16:35:29');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;

-- Dumping structure for table bookstore.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookstore.users: ~2 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `email`, `phone`, `fname`, `lname`, `type`) VALUES
	('gsmith', '123456', 'vbellos.dev@gmail.com', '6985648059', 'Κωστας', 'Μπελλος', 'Client'),
	('jwhite', '123456', 'vbellos.dev@gmail.com', '6985648059', 'ΕΥΑΓΓΕΛΟΣ', 'ΜΠΕΛΛΟΣ', 'Author'),
	('vbellos', '123456', 'vbellos.dev@gmail.com', '6985648059', 'ΕΥΑΓΓΕΛΟΣ', 'ΜΠΕΛΛΟΣ ', 'Client');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
