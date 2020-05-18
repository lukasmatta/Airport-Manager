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
}

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.scss']
})
export class FlightsComponent implements OnInit {
  dataSource = new MatTableDataSource<Flight>();
  dataColumns = ['id', 'origin', 'destination', 'departure', 'arrival', 'plane'];
  originID: number;
  destinationID: number;
  departure: string;
  arrival: string;
  planeID: number;
  errorMessage: string;

  allAirports: Airport[];
  allAirplanes: Airplane[];
  allStewards: Steward[];

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
    if (this.originID && this.destinationID && this.departure && this.arrival && this.planeID) {
      this.dataService.addFlight(this.originID, this.destinationID, this.departure, this.arrival, this.planeID).subscribe(
        (data: Flight) => {
          this.dataSource.data = this.dataSource.data.concat([{
            id: data.id, origin: data.origin, destination: data.destination, departure: data.departure, arrival: data.arrival, plane: data.plane}]);
        }
      );
    } else {
      this.errorMessage = 'All fields are required';
    }
    console.log(this.dataSource.data);
  }

}
