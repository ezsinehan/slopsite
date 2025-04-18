import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';
import { DashboardService } from '../dashboard.service';
import { User } from '../../models/user.model';
import { Course, CourseStudentsData } from '../../models/course.model';

@Component({
  standalone: false,
  selector: 'app-teacher-dashboard',
  templateUrl: './teacher-dashboard.component.html',
  styleUrl: './teacher-dashboard.component.css',
})
export class TeacherDashboardComponent {
  currentTeacher!: User;
  teacherCourses: Course[] = [];
  StudentsData: CourseStudentsData[] = [];

  constructor(
    private authService: AuthService,
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentTeacher = this.authService.getCurrentUser();
    this.getTeacherClasses();
  }

  getTeacherClasses(): void {
    this.dashboardService
      .getMyCoursesTeacher(this.currentTeacher.id)
      .subscribe({
        next: (courses) => {
          console.log('teacher courses:', courses);
          this.teacherCourses = courses.map(
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

  getStudentsForCourse(course: Course): void {
    this.dashboardService.getStudentsForCourse(course.courseId).subscribe({
      next: (courses) => {
        console.log('course student data:', courses);
        this.StudentsData = courses.map(
          (c: Partial<CourseStudentsData>): CourseStudentsData => ({
            studentName: c.studentName ?? 'namenotfound',
            studentId: c.studentId ?? -1,
            grade: c.grade ?? -1,
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

  updateGrade(data: CourseStudentsData, newGradeValue: string): void {
    const newGrade = Number(newGradeValue);

    if (isNaN(newGrade)) {
      console.error('Invalid grade input');
      return;
    }
    if (!data.enrollmentId) {
      console.error('no enrollment id: ', data);
      return;
    }

    this.dashboardService.updateGrade(data.enrollmentId, newGrade).subscribe({
      next: (res) => {
        console.log('Grade updated:', res);
        data.grade = newGrade;
      },
      error: (err) => {
        console.error('Failed to update grade:', err);
      },
    });
  }

  logout(): void {
    this.authService.logout();
    console.log('current teacher user:', this.authService.getCurrentUser());
    this.router.navigate(['/login']);
  }
}
