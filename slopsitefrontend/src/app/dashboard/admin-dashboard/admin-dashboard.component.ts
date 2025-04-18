import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';
import { DashboardService } from '../dashboard.service';
import { User, Enrollment } from '../../models/user.model';
import { Course, AdminCourse } from '../../models/course.model';

@Component({
  standalone: false,
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})
export class AdminDashboardComponent {
  allStudents: User[] = [];
  allTeachers: User[] = [];
  allCourses: AdminCourse[] = [];
  enrollments: Enrollment[] = [];

  constructor(
    private authService: AuthService,
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAllUsers();
    this.loadAllCourses();
    this.loadAllEnrollments();
  }

  loadAllUsers(): void {
    this.dashboardService.getAllStudents().subscribe({
      next: (students) => {
        console.log('Loaded students:', students);
        this.allStudents = students;
      },
      error: (err) => {
        console.error('Failed to load students:', err);
      },
    });

    this.dashboardService.getAllTeachers().subscribe({
      next: (teachers) => {
        console.log('Loaded teachers:', teachers);
        this.allTeachers = teachers;
      },
      error: (err) => {
        console.error('Failed to load teachers:', err);
      },
    });
  }

  loadAllCourses(): void {
    this.dashboardService.getAllCourses().subscribe({
      next: (courses) => {
        console.log(courses);
        this.allCourses = courses.map(
          (c: Partial<AdminCourse>): AdminCourse => ({
            id: c.id ?? -1,
            name: c.name ?? 'namenotfound',
            teacherName: c.teacherName ?? 'teachernamenotfound',
            time: c.time ?? 'timenotfound',
            currentCapacity: c.currentCapacity ?? -1,
            totalCapacity: c.totalCapacity ?? -1,
          })
        );
      },
      error: (err) => console.error('Failed to load courses:', err),
    });
  }

  loadAllEnrollments(): void {
    this.dashboardService.getAllEnrollments().subscribe({
      next: (data) => {
        console.log('Enrollments:', data);
        this.enrollments = data;
      },
      error: (err) => {
        console.error('Failed to load enrollments:', err);
      },
    });
  }

  createStudent(username: string, password: string, name: string): void {
    const newStudent = { username, password, name };

    this.dashboardService.createStudent(newStudent).subscribe({
      next: (createdStudent) => {
        console.log('Student created:', createdStudent);
        this.ngOnInit(); // refresh students list
      },
      error: (err) => {
        console.error('Failed to create student:', err);
      },
    });
  }

  createTeacher(username: string, password: string, name: string): void {
    const newTeacher = { username, password, name };

    this.dashboardService.createTeacher(newTeacher).subscribe({
      next: (createdTeacher) => {
        console.log('Teacher created:', createdTeacher);
        this.ngOnInit(); // refresh teacher list
      },
      error: (err) => {
        console.error('Failed to create teacher:', err);
      },
    });
  }

  // updateUserRole(userId: number, newRole: string): void {
  //   this.dashboardService.updateUserRole(userId, newRole).subscribe({
  //     next: () => this.loadAllUsers(),
  //     error: (err) => console.error('Role update failed:', err),
  //   });
  // }

  deleteStudent(id: number): void {
    this.dashboardService.deleteStudent(id).subscribe({
      next: () => {
        console.log('Deleted student with ID:', id);
        this.ngOnInit();
      },
      error: (err) => {
        console.error('Failed to delete student:', err);
      },
    });
  }

  deleteTeacher(id: number): void {
    this.dashboardService.deleteTeacher(id).subscribe({
      next: () => {
        console.log('Deleted teacher with ID:', id);
        this.ngOnInit();
      },
      error: (err) => {
        console.error('Failed to delete teacher:', err);
      },
    });
  }

  createCourse(
    name: string,
    time: string,
    totalCapacity: number,
    teacherId: number
  ): void {
    const newCourse = {
      name,
      time,
      totalCapacity,
      teacher: { id: teacherId },
    };

    this.dashboardService.createCourse(newCourse).subscribe({
      next: (res) => {
        console.log('Course created:', res);
        this.ngOnInit(); // refresh course list
      },
      error: (err) => {
        console.error('Failed to create course:', err);
      },
    });
  }

  deleteCourse(id: number): void {
    this.dashboardService.deleteCourse(id).subscribe({
      next: () => {
        console.log('Deleted course with ID:', id);
        this.ngOnInit(); // refresh course list
      },
      error: (err) => {
        console.error('Failed to delete course:', err);
      },
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
