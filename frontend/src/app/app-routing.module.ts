import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PractitionerComponent} from './component/practitioner/practitioner.component';
import {QuestionnaireComponent} from "./component/questionnaire/questionnaire.component";
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {PractitionerDetailComponent} from "./component/practitioner-detail/practitioner-detail.component";
import {InfoComponent} from "./component/info/info.component";
import {ReminderOverviewComponent} from "./component/reminder-overview/reminder-overview.component";
import {ReminderDetailComponent} from "./component/reminder-detail/reminder-detail.component";
import {ReminderCreateComponent} from "./component/reminder-create/reminder-create.component";
import {PractitionerSearchComponent} from "./component/practitioner-search/practitioner-search.component";
import {PractitionerCalendarComponent} from "./component/practitioner-calendar/practitioner-calendar.component";
import {BestaetigungComponent} from "./component/bestaetigung/bestaetigung.component";

const routes: Routes = [
  {path: '', redirectTo: 'practitioners', pathMatch: 'full'},
  {path: 'login', pathMatch: 'full', component: LoginComponent},
  {path: 'practitioners', component: PractitionerComponent},
  {path: 'practitioners/detail/:id', component: PractitionerDetailComponent},
  // {path: '**', redirectTo: 'practitioners'},
  {path: 'practitioners/:id/calendar', component: PractitionerCalendarComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'fragebogen', component: QuestionnaireComponent},
  {path: 'infos', component: InfoComponent},
  {path: 'termin-uebersicht', component: ReminderOverviewComponent},
  {path: 'termin/:id', component: ReminderDetailComponent},
  {path: 'termin/neu', component: ReminderCreateComponent},
  {path: 'practitioners/suche', component: PractitionerSearchComponent},
  {path: 'appointment/bestaetigung/:id', component: BestaetigungComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
