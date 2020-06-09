import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { toBase64String } from '@angular/compiler/src/output/source_map';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }


  public authenticate(username: string, password: string): Observable<any> {
    if (username === '' || password === '') {
      return null;
    }
    console.log("username:", username, "password", password);
    const authString = btoa(`${username}:${password}`);
    const authHeader: HttpHeaders = new HttpHeaders({Authorization: 'Basic ' + authString});

    return this.http.get(environment.restAPI + 'airports', {headers: authHeader});
  }

  public saveCredentials(username: string, password: string): void {
    sessionStorage.setItem('username', username);
    sessionStorage.setItem('password', password);
  }

  public isAuthenticated(): boolean {
    if (sessionStorage.getItem('username') && sessionStorage.getItem('password')) {
      return true;
    }
    return false;
  }

  public getEncodedCredentials(): string {
    const credentials = `${sessionStorage.getItem('username')}:${sessionStorage.getItem('password')}`;
    return btoa(credentials);
  }

  public isAdmin(): boolean {
    if (this.isAuthenticated()) {
      if (sessionStorage.getItem('username') === 'admin') {
        return true;
      }
    }
    return false;
  }

  public getUsername(): string {
    return sessionStorage.getItem('username');
  }
}
