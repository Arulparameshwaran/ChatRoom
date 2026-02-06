CREATE database chatdb
Use chatdb
CREATE TABLE chat_message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender VARCHAR(100),
    content TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO chat_message(sender, content)
VALUES ('System', 'Database connected successfully');
USE chatdb;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100)
);

select * from chat_message;
Use chatdb;
ALTER TABLE messages ADD sentiment VARCHAR(20);


