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
    return this.http.post(environment.restAPI + 'airplanes/auth/create', data);
  }

  public addFlight(originID: number, destinationID: number, departure: string, arrival: string, planeID: number, stewards: number[]) {
    const data = {'originID': originID, 'destinationID': destinationID, 'departure': departure, 'arrival': arrival, 'planeID': planeID, 'stewardsList': stewards};
    return this.http.post(environment.restAPI + 'flights/auth/create', data);
  }

  public removeFlight(id: number) {
    return this.http.delete(environment.restAPI + 'flights/auth/delete/' + id);
  }

  public removeAirplane(id: number) {
    return this.http.delete(environment.restAPI + 'airplanes/auth/delete/' + id);
  }

  public removeAirport(id: number) {
    return this.http.delete(environment.restAPI + 'airports/auth/delete/' + id);
  }

  public removeSteward(id: number) {
    return this.http.delete(environment.restAPI + 'stewards/auth/delete/' + id);
  }
}

