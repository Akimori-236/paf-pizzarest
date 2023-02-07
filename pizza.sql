CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`)
)

CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `pizza_id` int NOT NULL,
  `size_id` int NOT NULL,
  `quantity` int NOT NULL,
  `customer_id` int NOT NULL,
  `isRush` tinyint DEFAULT '0',
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `pizza_id_idx` (`pizza_id`),
  KEY `size_id_idx` (`size_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `pizza_id` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`pizza_id`),
  CONSTRAINT `size_id` FOREIGN KEY (`size_id`) REFERENCES `size` (`size_id`)
)

CREATE TABLE `pizza` (
  `pizza_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`pizza_id`)
)

INSERT INTO `pizza`
VALUES (1, 'bella', 30),
(2, 'marinara', 30),
(3, 'spianatacalabrese', 30),
(4, 'margherita', 22),
(5, 'trioformaggio', 25);

CREATE TABLE `size` (
  `size_id` int NOT NULL AUTO_INCREMENT,
  `size` varchar(8) NOT NULL,
  `multiplier` float NOT NULL,
  PRIMARY KEY (`size_id`)
)

INSERT INTO `size`
VALUES (1, 'sm', 1),
(2, 'md', 1.2),
(3, 'lg', 1.5);
