import { Component, OnInit } from '@angular/core';
import { Airport } from '../airports/airports.component';
import { Airplane } from '../airplanes/airplanes.component';
import { MatTableDataSource } from '@angular/material/table';
import { DataService } from 'src/app/services/data.service';
import { Steward } from '../stewards/stewards.component';

export interface Flight {
  id: number;
  origin: Airport;
  destination: Airport;
  departure: string;
  arrival: string;
  plane: Airplane;
  stewards: Steward[];
}

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.scss']
})
export class FlightsComponent implements OnInit {
  dataSource = new MatTableDataSource<Flight>();
  dataColumns = ['id', 'origin', 'destination', 'departure', 'arrival', 'plane', 'stewards'];
  originID: number;
  destinationID: number;
  departure: string;
  arrival: string;
  planeID: number;
  errorMessage: string;
  departureDate: string;
  departureTime: string;
  arrivalDate: string;
  arrivalTime: string;
  allAirports: Airport[];
  allAirplanes: Airplane[];
  allStewards: Steward[];
  selectedStewards: number[];

  constructor(private dataService: DataService) {
    this.dataService.fetchFlights().subscribe(
      data => {
        this.dataSource.data = data.content;
      }
    );

    this.dataService.fetchAirplanes().subscribe(
      data => {
        this.allAirplanes = data.content;
      }
    );

    this.dataService.fetchAirports().subscribe(
      data => {
        this.allAirports = data.content;
      }
    );

    this.dataService.fetchStewards().subscribe(
      data => {
        this.allStewards = data.content;
      }
    );
  }
  ngOnInit() {
  }

  addFlight() {
    let date = new Date(this.departureDate);
    let time = this.departureTime.split(':');
    date.setHours(Number(time[0]));
    date.setMinutes(Number(time[1]));
    this.departure = date.toUTCString();

    time = this.arrivalTime.split(':');
    date = new Date(this.arrivalDate);
    date.setHours(Number(time[0]));
    date.setMinutes(Number(time[1]));
    this.arrival = date.toUTCString();

    if (this.originID && this.destinationID && this.departureTime && this.departureDate && this.arrivalDate && this.arrivalTime && this.planeID) {
      this.dataService.addFlight(this.originID, this.destinationID, this.departure, this.arrival, this.planeID, this.selectedStewards).subscribe(
        (data: Flight) => {
          this.dataSource.data = this.dataSource.data.concat([{
            id: data.id, origin: data.origin, destination: data.destination, departure: data.departure, arrival: data.arrival, plane: data.plane, stewards: data.stewards}]);
        }
      );
    } else {
      this.errorMessage = 'All fields are required';
    }
    console.log(this.dataSource.data);
  }

  showStewards(stewards: Steward[]) {
    let result = '';
    for (let stw of stewards) {
      if (stw) {
        result += `${stw.firstName} ${stw.lastName}, `;
      }
    }
    console.log(stewards);
    return result.slice(0, -2);
  }

}
