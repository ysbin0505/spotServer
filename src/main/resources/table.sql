CREATE TABLE inquiry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    title VARCHAR(255),
    content VARCHAR(255),
    reg_date DATETIME  DEFAULT NOW()
)