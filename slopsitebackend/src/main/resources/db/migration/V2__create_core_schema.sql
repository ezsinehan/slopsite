-- V2__create_core_schema.sql

-- Students table
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- Teachers table
CREATE TABLE teachers (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- Courses table
CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    time VARCHAR(100) NOT NULL,
    current_capacity INTEGER NOT NULL,
    total_capacity INTEGER NOT NULL,
    teacher_id INTEGER,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id)
      REFERENCES teachers(id)
      ON DELETE SET NULL
);

-- Enrollments table
CREATE TABLE enrollments (
    id SERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL,
    course_id INTEGER NOT NULL,
    CONSTRAINT fk_student FOREIGN KEY (student_id)
        REFERENCES students(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (course_id)
        REFERENCES courses(id)
        ON DELETE CASCADE,
    CONSTRAINT unique_enrollment UNIQUE (student_id,course_id)
);

-- Create indexes on foreign keys for better performance
CREATE INDEX idx_courses_teacher_id ON courses(teacher_id);
CREATE INDEX idx_enrollments_student_id ON enrollments(student_id);
CREATE INDEX idx_enrollments_course_id ON enrollments(course_id);