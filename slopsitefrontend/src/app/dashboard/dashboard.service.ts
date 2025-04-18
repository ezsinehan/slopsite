import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';
import { User } from '../models/user.model';
import { forkJoin, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private baseUrl = 'http://localhost:8080/api'; // adjust if needed

  constructor(private http: HttpClient) {}

  getAllStudents(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/admin/students');
  }

  getAllTeachers(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/admin/teachers');
  }

  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>('http://localhost:8080/admin/courses');
  }

  createStudent(student: {
    username: string;
    password: string;
    name: string;
  }): Observable<User> {
    return this.http.post<User>(
      'http://localhost:8080/admin/students',
      student,
      {
        headers: { 'Content-Type': 'application/json' },
      }
    );
  }

  deleteStudent(id: number) {
    return this.http.delete(`http://localhost:8080/admin/students/${id}`);
  }

  deleteTeacher(id: number) {
    return this.http.delete(`http://localhost:8080/admin/teachers/${id}`);
  }

  // updateUserRole(userId: number, newRole: string): Observable<void> {
  //   return this.http.put<void>(`/api/users/${userId}/role`, { role: newRole });
  // }
  getMyCoursesStudent(studentId: number): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${this.baseUrl}/enrollments/courses-by-student/${studentId}`
    );
  }

  getMyCoursesTeacher(teacherId: number): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${this.baseUrl}/courses/by-teacher/${teacherId}`
    );
  }

  getStudentsForCourse(courseId: number): Observable<any> {
    return this.http.get<Course[]>(
      `${this.baseUrl}/enrollments/by-course/${courseId}`
    );
  }

  getAllCoursesStudent(studentId: number): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${this.baseUrl}/courses/with-enrollment-status/${studentId}`
    );
  }

  updateGrade(enrollmentId: number, newGrade: number) {
    return this.http.put(
      `${this.baseUrl}/enrollments/${enrollmentId}/grade`,
      {},
      {
        params: { grade: newGrade.toString() },
        responseType: 'text' as const, // 👈 treat response as plain text
      }
    );
  }

  enroll(studentId: number, courseId: number): Observable<any> {
    const payload = {
      student: { id: studentId },
      course: { id: courseId },
      grade: -1,
    };

    return this.http.post<any>(`${this.baseUrl}/enrollments`, payload, {
      headers: { 'Content-Type': 'application/json' },
    });
  }

  drop(enrollmentId: number): Observable<any> {
    return this.http.delete<any>(
      `${this.baseUrl}/enrollments/${enrollmentId}`,
      {
        headers: { 'Content-Type': 'application/json' },
      }
    );
  }
}
