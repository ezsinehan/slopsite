export interface Course {
  courseName: string;
  courseId: number;
  teacherName: string;
  time: string;
  currentEnrollment: string;
  capacity: string;
  enrolled: boolean | null;
  enrollmentId: number | null;
}

export interface CourseStudentsData {
  studentId: number;
  studentName: string;
  grade: number;
  enrollmentId: number | null;
}

export interface AdminCourse {
  id: number;
  name: string;
  time: string;
  currentCapacity: number;
  totalCapacity: number;
}
