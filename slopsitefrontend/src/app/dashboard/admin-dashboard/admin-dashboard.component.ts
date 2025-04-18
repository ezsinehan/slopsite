import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';
import { DashboardService } from '../dashboard.service';
import { User } from '../../models/user.model';
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

  constructor(
    private authService: AuthService,
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAllUsers();
    this.loadAllCourses();
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
            time: c.time ?? 'timenotfound',
            currentCapacity: c.currentCapacity ?? -1,
            totalCapacity: c.totalCapacity ?? -1,
          })
        );
      },
      error: (err) => console.error('Failed to load courses:', err),
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

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
