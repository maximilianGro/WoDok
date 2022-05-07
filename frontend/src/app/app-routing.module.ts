import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PractitionerComponent} from './component/practitioner/practitioner.component';

const routes: Routes = [
  {path: '', redirectTo: 'practitioners', pathMatch: 'full'},
  {path: 'practitioners', component: PractitionerComponent},
  {path: '**', redirectTo: 'practitioners'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
