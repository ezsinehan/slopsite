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
