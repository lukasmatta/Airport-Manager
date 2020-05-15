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

    const authString = btoa('${username} ${password}');
    const authHeader: HttpHeaders = new HttpHeaders({Authorization: authString});

    return this.http.get(environment.restAPI, {headers: authHeader});
  }
}
