CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(30) NOT NULL,
    `lastname` varchar(30) NOT NULL,
    `dni` varchar(10) NOT NULL,
    `email` varchar(30) NOT NULL,
    `created_at` date  NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `updated_at` date  NOT NULL,
    `updated_by` varchar(30) NOT NULL

);
