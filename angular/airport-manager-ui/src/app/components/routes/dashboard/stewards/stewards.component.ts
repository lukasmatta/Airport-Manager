import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { DataService } from 'src/app/services/data.service';
import { AuthService } from 'src/app/services/auth.service';

export interface Steward {
  id: number;
  firstName: string;
  lastName: string;
}

@Component({
  selector: 'app-stewards',
  templateUrl: './stewards.component.html',
  styleUrls: ['./stewards.component.scss']
})
export class StewardsComponent implements OnInit {

  dataSource = new MatTableDataSource<Steward>();
  dataColumns = ['id', 'firstName', 'lastName', 'actions'];
  firstName: string;
  lastName: string;
  errorMessage: string;
  isAdmin: boolean;

  constructor(private dataService: DataService, private auth: AuthService) {
    this.isAdmin = this.auth.isAdmin();

    this.dataService.fetchStewards().subscribe(
      data => {
        this.dataSource.data = data.content;
      }
    );
  }

  ngOnInit() {
  }

  addSteward() {
    if (this.firstName && this.lastName) {
      this.dataService.addSteward(this.firstName, this.lastName).subscribe(
        (data: Steward) => {
          this.dataSource.data = this.dataSource.data.concat([{id: data.id, firstName: data.firstName, lastName: data.lastName}]);
        }
      );
    } else {
      this.errorMessage = 'firstName and lastName are required';
    }
    console.log(this.dataSource.data);
  }

  removeSteward(id: number) {
    this.dataService.removeSteward(id).subscribe(
      res => {
        this.dataSource.data = this.dataSource.data.filter(f => f.id !== id);
      }
    );
  }

}
