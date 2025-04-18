import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';
import { DashboardService } from '../dashboard.service';
import { User } from '../../models/user.model';
import { Course } from '../../models/course.model';

@Component({
  standalone: false,
  selector: 'app-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.css'],
})
export class StudentDashboardComponent {
  currentStudent!: User;

  constructor(
    private authService: AuthService,
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  studentCourses: Course[] = [];
  allCourses: Course[] = [];

  ngOnInit(): void {
    this.currentStudent = this.authService.getCurrentUser();
    this.getStudentClasses();
    this.getAllCourses();
  }

  getStudentClasses(): void {
    this.dashboardService
      .getMyCoursesStudent(this.currentStudent.id)
      .subscribe({
        next: (courses) => {
          this.studentCourses = courses.map(
            (c: Partial<Course>): Course => ({
              courseName: c.courseName ?? 'namenotfound',
              courseId: c.courseId ?? -1,
              teacherName: c.teacherName ?? 'teachernotfound',
              time: c.time ?? 'timenotfound',
              currentEnrollment: c.currentEnrollment ?? '-1',
              capacity: c.capacity ?? '-1',
              enrolled: c.enrolled ?? null,
              enrollmentId: c.enrollmentId ?? null,
            })
          );
          console.log('student classes received in component:', courses);
        },
        error: (err) => {
          console.error('failed to fetch student courses:', err);
        },
      });
  }

  getAllCourses(): void {
    this.dashboardService
      .getAllCoursesStudent(this.currentStudent.id)
      .subscribe({
        next: (courses) => {
          console.log('all da courses: ', courses);
          this.allCourses = courses.map(
            (c: Partial<Course>): Course => ({
              courseName: c.courseName ?? 'namenotfound',
              courseId: c.courseId ?? -1,
              teacherName: c.teacherName ?? 'teachernotfound',
              time: c.time ?? 'timenotfound',
              currentEnrollment: c.currentEnrollment ?? '-1',
              capacity: c.capacity ?? '-1',
              enrolled: c.enrolled ?? false,
              enrollmentId: c.enrollmentId ?? null,
            })
          );
        },
        error: (err) => {
          console.error('failed to fetch all courses:', err);
        },
      });
  }

  enroll(course: Course): void {
    this.dashboardService
      .enroll(this.currentStudent.id, course.courseId)
      .subscribe({
        next: (res) => {
          console.log('enrolling in course:', course);
          console.log('da enrollment: ', res);
          this.ngOnInit();
        },
        error: (err) => {
          console.error('failed to enroll:', err);
        },
      });
  }

  drop(course: Course): void {
    if (course.enrollmentId === null) {
      console.error('Cannot drop: enrollmentId is null');
      return;
    }

    this.dashboardService.drop(course.enrollmentId).subscribe({
      next: (res) => {
        console.log('dropping course:', course);
        console.log('da drop: ', res);
        this.ngOnInit();
      },
      error: (err) => {
        console.error('failed to drop:', err);
      },
    });
  }

  logout(): void {
    this.authService.logout();
    console.log('current user:', this.authService.getCurrentUser());
    this.router.navigate(['/login']);
  }
}
