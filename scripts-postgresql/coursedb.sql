CREATE TABLE courses (
                         id BIGSERIAL PRIMARY KEY,
                         title VARCHAR(200) NOT NULL,
                         description TEXT,
                         published BOOLEAN DEFAULT FALSE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);