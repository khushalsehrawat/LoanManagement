-- =========================
-- LAMF LMS - data.sql (PostgreSQL)
-- Seed data for quick demo + video
-- =========================

-- 1) Partner API client
INSERT INTO api_clients (client_name, api_key)
VALUES ('DemoFintech', 'DEMO_PARTNER_KEY_123');

-- 2) Loan products
-- id will be 1,2 if fresh DB
INSERT INTO loan_products (name, interest_rate, max_ltv, min_amount, max_amount)
VALUES
  ('LAMF Overdraft', 14.5, 0.50, 10000, 500000),
  ('LAMF Term Loan', 16.0, 0.45, 10000, 300000);

-- 3) Customers
INSERT INTO customers (full_name, email, phone, pan)
VALUES
  ('Rahul Sharma', 'rahul@example.com', '9999999991', 'ABCDE1234F'),
  ('Neha Verma', 'neha@example.com', '9999999992', 'PQRSX9876K');

-- 4) Loan Applications
-- App 1: DRAFT (no collateral yet) - Rahul, Overdraft
INSERT INTO loan_applications (customer_id, loan_product_id, requested_amount, eligible_limit, status, created_at)
VALUES (1, 1, 80000, 0, 'DRAFT', NOW());

-- App 2: SUBMITTED (has collateral) - Neha, Term Loan
INSERT INTO loan_applications (customer_id, loan_product_id, requested_amount, eligible_limit, status, created_at)
VALUES (2, 2, 120000, 0, 'SUBMITTED', NOW());

-- App 3: ACTIVE (already approved + disbursed) - Rahul, Overdraft
INSERT INTO loan_applications (customer_id, loan_product_id, requested_amount, eligible_limit, status, created_at)
VALUES (1, 1, 150000, 0, 'ACTIVE', NOW());

-- 5) Collaterals
-- For App 2 (SUBMITTED) - UNPLEDGED
INSERT INTO collaterals (loan_application_id, scheme_name, units, nav, collateral_value, status)
VALUES
  (2, 'HDFC Flexi Cap Fund', 300, 220, 66000, 'UNPLEDGED'),
  (2, 'SBI Bluechip Fund', 150, 180, 27000, 'UNPLEDGED');

UPDATE loan_applications
SET eligible_limit = 93000 * 0.45
WHERE id = 2;

-- For App 3 (ACTIVE) - PLEDGED
INSERT INTO collaterals (loan_application_id, scheme_name, units, nav, collateral_value, status)
VALUES
  (3, 'ICICI Prudential Equity Fund', 500, 250, 125000, 'PLEDGED');

UPDATE loan_applications
SET eligible_limit = 125000 * 0.50
WHERE id = 3;

-- 6) Loan (for App 3)
INSERT INTO loans (loan_application_id, sanctioned_limit, outstanding_amount, status, created_at)
VALUES (3, 62500, 62500, 'ACTIVE', NOW());

-- 7) Repayments (for Loan 1)
INSERT INTO repayments (loan_id, amount, paid_at)
VALUES
  (1, 10000, NOW()),
  (1, 12500, NOW());

UPDATE loans
SET outstanding_amount = 40000
WHERE id = 1;
