import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/routes/login/login.component';
import { DashboardComponent } from './components/routes/dashboard/dashboard.component';
import { FlightsComponent } from './components/routes/dashboard/flights/flights.component';
import { AirportsComponent } from './components/routes/dashboard/airports/airports.component';
import { AirplanesComponent } from './components/routes/dashboard/airplanes/airplanes.component';
import { StewardsComponent } from './components/routes/dashboard/stewards/stewards.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard',   redirectTo: '/dashboard/airplanes', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], children:
    [
      { path: 'flights', component: FlightsComponent },
      { path: 'airports', component: AirportsComponent },
      { path: 'airplanes', component: AirplanesComponent },
      { path: 'stewards', component: StewardsComponent }
    ]
  },
  { path: '',   redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '**', redirectTo: '/dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
