import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private baseUrl = 'http://localhost:8080/api'; // adjust if needed

  constructor(private http: HttpClient) {}
  getMyCoursesStudent(studentId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.baseUrl}/enrollments/courses-by-student/${studentId}`
    );
  }
}
