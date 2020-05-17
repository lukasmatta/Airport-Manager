import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class DataService {

  constructor(private http: HttpClient) { }

  public fetchAirports(): Observable<any> {
    return this.http.get(environment.restAPI + 'airports');
  }

  public fetchStewards(): Observable<any> {
    return this.http.get(environment.restAPI + 'stewards');
  }

  public addAirport(city: string, country: string) {
    const data = {'city': city, 'country': country};
    return this.http.post(environment.restAPI + 'airports/auth/create', data);
  }

  public addSteward(firstName: string, lastName: string) {
    const data = {'firstName': firstName, 'lastName': lastName};
    return this.http.post(environment.restAPI + 'stewards/auth/create', data);
  }
}

