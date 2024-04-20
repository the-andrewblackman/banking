DROP TABLE trxnsxctions CASCADE;
DROP TABLE savings CASCADE;
DROP TABLE checking CASCADE;
DROP TABLE account CASCADE;


CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE checking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    available_balance INT,
    present_balance INT,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE savings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    available_balance INT,
    present_balance INT,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE trxnsxctions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trxnsxctiontype VARCHAR(255) NOT NULL,
    trxnsxctiondescription TEXT NOT NULL,
    amount BIGINT,
    checking_id BIGINT,
    savings_id BIGINT,
    FOREIGN KEY (checking_id) REFERENCES checking(id),
    FOREIGN KEY (savings_id) REFERENCES savings(id)
);