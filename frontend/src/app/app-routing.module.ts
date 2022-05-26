import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PractitionerComponent} from './component/practitioner/practitioner.component';
import {QuestionnaireComponent} from "./component/questionnaire/questionnaire.component";
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {PractitionerDetailComponent} from "./component/practitioner-detail/practitioner-detail.component";

const routes: Routes = [
  {path: '', redirectTo: 'practitioners', pathMatch: 'full'},
  {path: 'login', pathMatch: 'full', component: LoginComponent},
  {path: 'practitioners', component: PractitionerComponent},
  {path: 'practitioners/:id', component: PractitionerDetailComponent},
  // {path: '**', redirectTo: 'practitioners'},
  {path: 'register', component: RegisterComponent},
  {path: 'fragebogen', component: QuestionnaireComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
