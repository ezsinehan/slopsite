-- V3__add_grade_to_enrollments.sql
ALTER TABLE enrollments
ADD COLUMN grade VARCHAR(10);
