import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  hide = true;
  username: string;
  password: string;
  errorMessage = '';

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() { }

  public login() {
    this.auth.authenticate(this.username, this.password).subscribe(
      response => {
        if (response) {
          sessionStorage.setItem('username', this.username);
          sessionStorage.setItem('password', this.password);
          this.errorMessage = '';
          this.router.navigate(['dashboard/airplanes']);
        }
      },
      error => {
        this.errorMessage = 'Wrong credentials';
      }
    );
  }

}
