-- payment-service: paymentdb
CREATE TABLE IF NOT EXISTS payments (
    id BIGSERIAL PRIMARY KEY,
    enrollment_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) DEFAULT 'APPROVED',
    rejection_reason VARCHAR(255),
    paid_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
