CREATE DATABASE course_database;

USE course_database;

CREATE TABLE courses (
    code VARCHAR(10) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    capacity INT NOT NULL,
    schedule VARCHAR(100) NOT NULL
);



CREATE TABLE students (
    StudentID VARCHAR(9) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    registered_course VARCHAR(10),
    FOREIGN KEY (registered_course) REFERENCES courses(code)
);

-- Insert sample data into the courses table
INSERT INTO courses (code, title, description, capacity, schedule) VALUES
    ('C001', 'Java Programming', 'Introduction to Java programming', 3, 'Mon/Wed 10:00 AM - 12:00 PM'),
    ('C002', 'Database Management', 'Fundamentals of database management', 25, 'Tue/Thu 2:00 PM - 4:00 PM'),
    ('C003', 'Web Development', 'Building web applications with HTML, CSS, and JavaScript', 20, 'Wed/Fri 1:00 PM - 3:00 PM'),
    ('C004', 'Algorithms & DS', 'Advanced algorithms and data structures', 35, 'Mon/Wed 2:00 PM - 4:00 PM'),
    ('C005', 'Mobile App Dev', 'Creating mobile apps for iOS and Android', 28, 'Tue/Thu 10:00 AM - 12:00 PM');
    
    
    