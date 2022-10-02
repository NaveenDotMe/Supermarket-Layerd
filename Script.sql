DROP DATABASE IF EXISTS supermarket;
CREATE DATABASE IF NOT EXISTS supermarket;
SHOW DATABASES ;
USE supermarket;

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer(
    custId VARCHAR(6),
    custTitle VARCHAR(5),
    custName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    custAddress VARCHAR(30),
    city VARCHAR(20),
    province VARCHAR(20),
    postalCode VARCHAR(9),
    CONSTRAINT PRIMARY KEY (custId)
    );
SHOW TABLES ;
DESCRIBE customer;

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders(
    orderId VARCHAR(15),
    custId VARCHAR(15),
    orderDate DATE,
    orderTime TIME,
    total DECIMAL(8,2),
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN KEY (custId) REFERENCES customer (custId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE orders;

DROP TABLE IF EXISTS item;
CREATE TABLE IF NOT EXISTS item(
    itemCode VARCHAR(6),
    description VARCHAR(50),
    packSize VARCHAR(20),
    unitPrice DECIMAL(6,2),
    qtyOnHand INT(5),
    CONSTRAINT PRIMARY KEY (itemCode)
    );
SHOW TABLES ;
DESCRIBE item;

DROP TABLE IF EXISTS order_detail;
CREATE TABLE IF NOT EXISTS order_detail(
    orderId VARCHAR(15),
    itemCode VARCHAR(15),
    orderQty INT(11),
    discount DECIMAL(6,2),
    CONSTRAINT PRIMARY KEY (orderId, itemCode),
    CONSTRAINT FOREIGN KEY (itemCode) REFERENCES item (itemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders (orderId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE order_detail;

DROP TABLE IF EXISTS login;
CREATE TABLE IF NOT EXISTS login(
    user_name VARCHAR(25) NOT NULL,
    password VARCHAR (25) NOT NULL,
    CONSTRAINT PRIMARY KEY (user_name)
    );
SHOW TABLES ;
DESCRIBE login;

SELECT itemCode, SUM(orderQty) FROM order_detail GROUP BY itemCode ORDER BY orderQty DESC;

SELECT custId, SUM(total) FROM orders GROUP BY custId ORDER BY total DESC;

SELECT orderDate, SUM(total) FROM orders GROUP BY orderDate ORDER BY total DESC;

SELECT extract(MONTH FROM(orderDate)) ,sum(order_detail.orderQty),count(orders.orderId),sum(orders.total)  FROM orders INNER JOIN order_detail ON orders.orderId = order_detail.orderId GROUP BY orderDate;

SELECT extract(MONTH FROM(orderDate)) ,sum(total) FROM orders GROUP BY extract(MONTH FROM(orderDate)) ORDER BY total DESC;

SELECT extract(YEAR FROM(orderDate)) ,sum(total) FROM orders GROUP BY extract(YEAR FROM(orderDate)) ORDER BY total DESC;