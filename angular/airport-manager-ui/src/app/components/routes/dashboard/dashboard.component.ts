import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  username: string;

  constructor(private router: Router, private auth: AuthService) {
    this.username = this.auth.getUsername();
  }

  ngOnInit() {
  }

  logout() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('password');
    this.router.navigateByUrl('/login');

  }

}
