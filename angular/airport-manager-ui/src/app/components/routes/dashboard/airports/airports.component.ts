import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { DataService } from 'src/app/services/data.service';

export interface Airport {
  id: number;
  city: string;
  country: string;
}

const AIRPORTS: Airport[] = [
  {id: 1, city: 'Kosice', country: 'Slovakia'},
  {id: 2, city: 'Brno', country: 'Czechia'}
];

@Component({
  selector: 'app-airports',
  templateUrl: './airports.component.html',
  styleUrls: ['./airports.component.scss']
})
export class AirportsComponent implements OnInit {
  dataSource = new MatTableDataSource<Airport>();
  dataColumns = ['id', 'city', 'country'];
  city: string;
  country: string;
  errorMessage: string;

  constructor(private dataService: DataService) {
    this.dataService.fetchAirports().subscribe(
      data => {
        this.dataSource.data = data.content;
      }
    );
  }

  ngOnInit() {
  }

  addAirport() {
    if (this.city && this.country) {
      this.dataService.addAirport(this.city, this.country).subscribe(
        (data: Airport) => {
          this.dataSource.data = this.dataSource.data.concat([{id: data.id, city: data.city, country: data.country}]);
        }
      );
    } else {
      this.errorMessage = 'City and country are required';
    }
    console.log(this.dataSource.data);
  }

}