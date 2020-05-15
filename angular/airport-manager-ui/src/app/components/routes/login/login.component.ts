import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  hide = true;
  username: string;
  password: string;

  constructor(private auth: AuthService) { }

  ngOnInit() {
  }

  public login() {
    this.auth.authenticate(this.username, this.password).subscribe(
      response => {
        console.log(response)
      }
    );
  }

}
