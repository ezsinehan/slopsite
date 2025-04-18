import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';
import { DashboardService } from '../dashboard.service';
import { User } from '../../models/user.model';
import { Course } from '../../models/course.model';

@Component({
  standalone: false,
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class StudentDashboardComponent {
  currentStudent!: User;

  constructor(
    private authService: AuthService,
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  studentCourses: Course[] = [];

  ngOnInit(): void {
    this.currentStudent = this.authService.getCurrentUser();
    this.getStudentClasses();
  }

  getStudentClasses(): void {
    this.dashboardService
      .getMyCoursesStudent(this.currentStudent.id)
      .subscribe({
        next: (courses) => {
          this.studentCourses = courses;
          console.log('student classes received in component:', courses);
        },
        error: (err) => {
          console.error('failed to fetch student courses:', err);
        },
      });
  }

  logout(): void {
    this.authService.logout();
    console.log('current user:', this.authService.getCurrentUser());
    this.router.navigate(['/login']);
  }
}
