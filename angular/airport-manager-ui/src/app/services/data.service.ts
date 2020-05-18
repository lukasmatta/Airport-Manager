import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AirplaneType } from '../components/routes/dashboard/airplanes/airplanes.component';

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

  public fetchAirplanes(): Observable<any> {
    return this.http.get(environment.restAPI + 'airplanes');
  }

  public fetchFlights(): Observable<any> {
    return this.http.get(environment.restAPI + 'flights');
  }

  public addAirport(city: string, country: string) {
    const data = {'city': city, 'country': country};
    return this.http.post(environment.restAPI + 'airports/auth/create', data);
  }

  public addSteward(firstName: string, lastName: string) {
    const data = {'firstName': firstName, 'lastName': lastName};
    return this.http.post(environment.restAPI + 'stewards/auth/create', data);
  }

  public addAirplane(name: string, type: AirplaneType, capacity: number) {
    const data = {'name': name, 'type': type, 'capacity': capacity};
    return this.http.post(environment.restAPI + 'airplanes/create', data);
  }

  public addFlight(originID: number, destinationID: number, departure: string, arrival: string, planeID: number) {
    const data = {'originID': originID, 'destinationID': destinationID, 'departure': departure, 'arrival': arrival, 'planeID': planeID};
    return this.http.post(environment.restAPI + 'flights/auth/create', data);
  }
}

