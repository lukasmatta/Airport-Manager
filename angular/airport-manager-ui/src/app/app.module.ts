import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/routes/login/login.component';
import { DashboardComponent } from './components/routes/dashboard/dashboard.component';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';
import { FlightsComponent } from './components/routes/dashboard/flights/flights.component';
import { AirplanesComponent } from './components/routes/dashboard/airplanes/airplanes.component';
import { AirportsComponent } from './components/routes/dashboard/airports/airports.component';
import { StewardsComponent } from './components/routes/dashboard/stewards/stewards.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    FlightsComponent,
    AirplanesComponent,
    AirportsComponent,
    StewardsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
