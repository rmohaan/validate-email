-- Emails Table
CREATE TABLE source_emails (
    id BIGINT PRIMARY KEY,
    email VARCHAR(255)
);

-- Result Table
CREATE TABLE email_validation_results (
    id BIGINT PRIMARY KEY,
    email VARCHAR(255),
    status VARCHAR(10) -- 'valid' or 'invalid'
);
