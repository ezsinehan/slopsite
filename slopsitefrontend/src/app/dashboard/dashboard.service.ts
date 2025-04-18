import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private baseUrl = 'http://localhost:8080/api'; // adjust if needed

  constructor(private http: HttpClient) {}
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

  // getStudentsForCourse();

  getAllCoursesStudent(studentId: number): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${this.baseUrl}/courses/with-enrollment-status/${studentId}`
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
