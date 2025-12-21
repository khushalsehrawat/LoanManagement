-- =========================
-- LAMF LMS - schema.sql (PostgreSQL)
-- =========================

-- Drop in dependency order using CASCADE for clean reruns
DROP TABLE IF EXISTS repayments CASCADE;
DROP TABLE IF EXISTS loans CASCADE;
DROP TABLE IF EXISTS collaterals CASCADE;
DROP TABLE IF EXISTS loan_applications CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS loan_products CASCADE;
DROP TABLE IF EXISTS api_clients CASCADE;

-- -------------------------
-- api_clients
-- -------------------------
CREATE TABLE api_clients (
    id BIGSERIAL PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    api_key VARCHAR(255) NOT NULL UNIQUE
);

-- -------------------------
-- loan_products
-- -------------------------
CREATE TABLE loan_products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    interest_rate DOUBLE PRECISION NOT NULL,
    max_ltv DOUBLE PRECISION NOT NULL,
    min_amount DOUBLE PRECISION NOT NULL,
    max_amount DOUBLE PRECISION NOT NULL
);

-- -------------------------
-- customers
-- -------------------------
CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    pan VARCHAR(50) NOT NULL UNIQUE
);

-- -------------------------
-- loan_applications
-- -------------------------
CREATE TABLE loan_applications (
    id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT NOT NULL,
    loan_product_id BIGINT NOT NULL,

    requested_amount DOUBLE PRECISION NOT NULL,
    eligible_limit DOUBLE PRECISION NOT NULL,

    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_la_customer
        FOREIGN KEY (customer_id) REFERENCES customers(id),

    CONSTRAINT fk_la_product
        FOREIGN KEY (loan_product_id) REFERENCES loan_products(id)
);

CREATE INDEX idx_loan_applications_status
    ON loan_applications(status);

CREATE INDEX idx_loan_applications_customer
    ON loan_applications(customer_id);

-- -------------------------
-- collaterals
-- -------------------------
CREATE TABLE collaterals (
    id BIGSERIAL PRIMARY KEY,

    loan_application_id BIGINT NOT NULL,

    scheme_name VARCHAR(255) NOT NULL,
    units DOUBLE PRECISION NOT NULL,
    nav DOUBLE PRECISION NOT NULL,
    collateral_value DOUBLE PRECISION NOT NULL,

    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_collateral_application
        FOREIGN KEY (loan_application_id)
        REFERENCES loan_applications(id)
);

CREATE INDEX idx_collaterals_application
    ON collaterals(loan_application_id);

-- -------------------------
-- loans
-- -------------------------
CREATE TABLE loans (
    id BIGSERIAL PRIMARY KEY,

    loan_application_id BIGINT NOT NULL UNIQUE,

    sanctioned_limit DOUBLE PRECISION NOT NULL,
    outstanding_amount DOUBLE PRECISION NOT NULL,

    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_loan_application
        FOREIGN KEY (loan_application_id)
        REFERENCES loan_applications(id)
);

CREATE INDEX idx_loans_status
    ON loans(status);

-- -------------------------
-- repayments
-- -------------------------
CREATE TABLE repayments (
    id BIGSERIAL PRIMARY KEY,

    loan_id BIGINT NOT NULL,

    amount DOUBLE PRECISION NOT NULL,
    paid_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_repayment_loan
        FOREIGN KEY (loan_id)
        REFERENCES loans(id)
);

CREATE INDEX idx_repayments_loan
    ON repayments(loan_id);
