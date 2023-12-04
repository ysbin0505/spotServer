CREATE TABLE inquiry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    title VARCHAR(255),
    content VARCHAR(255),
    reg_date DATETIME  DEFAULT NOW()
)

CREATE TABLE comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inquiry_id BIGINT NOT NULL,
    name VARCHAR(255),
    content VARCHAR(255),
    reg_date DATETIME DEFAULT NOW(),
    FOREIGN KEY (inquiry_id) REFERENCES inquiry(id)
);