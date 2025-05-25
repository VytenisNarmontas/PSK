-- db-init.sql
CREATE DATABASE IF NOT EXISTS pskdb
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pskdb;

CREATE TABLE university (
                            id   BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE student (
                         id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name           VARCHAR(255) NOT NULL,
                         university_id  BIGINT,
                         FOREIGN KEY (university_id) REFERENCES university(id)
);

CREATE TABLE course (
                        id   BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE student_course (
                                student_id BIGINT NOT NULL,
                                course_id  BIGINT NOT NULL,
                                PRIMARY KEY (student_id, course_id),
                                FOREIGN KEY (student_id) REFERENCES student(id),
                                FOREIGN KEY (course_id ) REFERENCES course(id)
);
