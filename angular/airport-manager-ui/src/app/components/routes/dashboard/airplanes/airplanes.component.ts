import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { DataService } from 'src/app/services/data.service';

export enum AirplaneType {
  COMMERCIAL,
  PRIVATE,
  PROPELLER
}

export interface Airplane {
  id: number;
  name: string;
  type: AirplaneType;
  capacity: number;
}


@Component({
  selector: 'app-airplanes',
  templateUrl: './airplanes.component.html',
  styleUrls: ['./airplanes.component.scss']
})
export class AirplanesComponent implements OnInit {
  dataSource = new MatTableDataSource<Airplane>();
  dataColumns = ['id', 'name', 'type', 'capacity'];
  name: string;
  type: AirplaneType;
  capacity: number;
  errorMessage: string;
  airplaneTypes: string[] = [];

  constructor(private dataService: DataService) {
    this.dataService.fetchAirplanes().subscribe(
      data => {
        this.dataSource.data = data.content;
      }
    );
  }

  ngOnInit() {
    for (let item in Object.keys(AirplaneType).filter(key => isNaN(Number(key)))) {
      // if (item != null) {
        // console.log('item in enum:' + AirplaneType[item]);
        this.airplaneTypes.push(AirplaneType[item]);
      // }
    }
  }

  addAirplane() {
    if (this.name && this.capacity && this.type) {
      this.dataService.addAirplane(this.name, this.type, this.capacity).subscribe(
        (data: Airplane) => {
          this.dataSource.data = this.dataSource.data.concat([{id: data.id, name: data.name, type: data.type, capacity: data.capacity}]);
        }
      );
    } else {
      this.errorMessage = 'Name capacity and type are required';
    }
    console.log(this.dataSource.data);
  }

}
