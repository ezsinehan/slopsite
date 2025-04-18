-- V3__add_grade_column_to_enrollments.sql

ALTER TABLE enrollments
ADD COLUMN grade INTEGER;
