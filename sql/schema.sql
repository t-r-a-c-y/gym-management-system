CREATE DATABASE IF NOT EXISTS gms_db;
USE gms_db;

CREATE TABLE branch (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        location VARCHAR(255),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      role ENUM('ADMIN', 'TRAINER', 'MEMBER') NOT NULL,
                      branch_id BIGINT,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (branch_id) REFERENCES branch(id)
);

CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          trainer_id BIGINT,
                          class_name VARCHAR(255) NOT NULL,
                          start_time DATETIME NOT NULL,
                          end_time DATETIME NOT NULL,
                          capacity INT,
                          branch_id BIGINT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (trainer_id) REFERENCES user(id),
                          FOREIGN KEY (branch_id) REFERENCES branch(id)
);

CREATE TABLE class_attendance (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  member_id BIGINT,
                                  schedule_id BIGINT,
                                  status ENUM('PRESENT', 'ABSENT') NOT NULL,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  FOREIGN KEY (member_id) REFERENCES user(id),
                                  FOREIGN KEY (schedule_id) REFERENCES schedule(id)
);

CREATE TABLE class_booking (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               member_id BIGINT,
                               schedule_id BIGINT,
                               status ENUM('PENDING', 'CONFIRMED', 'CANCELLED') NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (member_id) REFERENCES user(id),
                               FOREIGN KEY (schedule_id) REFERENCES schedule(id)
);

CREATE TABLE membership_payment (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    member_id BIGINT,
                                    amount DOUBLE NOT NULL,
                                    payment_date VARCHAR(10) NOT NULL,
                                    status ENUM('PAID', 'PENDING', 'FAILED') NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    FOREIGN KEY (member_id) REFERENCES user(id)
);