import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './component/header/header.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModalModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {PractitionerComponent} from './component/practitioner/practitioner.component';
import {QuestionnaireComponent} from './component/questionnaire/questionnaire.component';
import {LoginComponent} from './component/login/login.component';
import {RegisterComponent} from './component/register/register.component';
import {PractitionerDetailComponent} from './component/practitioner-detail/practitioner-detail.component';
import {ReminderDetailComponent} from './component/reminder-detail/reminder-detail.component';
import {ReminderOverviewComponent} from './component/reminder-overview/reminder-overview.component';
import {ReminderCreateComponent} from './component/reminder-create/reminder-create.component';
import {InfoComponent} from './component/info/info.component';
import {PractitionerSearchComponent} from './component/practitioner-search/practitioner-search.component';
//Calendar
import {CalendarDateFormatter, CalendarModule, DateAdapter} from 'angular-calendar';
import {adapterFactory} from 'angular-calendar/date-adapters/date-fns';
import {CommonModule, registerLocaleData} from '@angular/common';
import {FlatpickrModule} from 'angularx-flatpickr';
import {PractitionerCalendarComponent} from './component/practitioner-calendar/practitioner-calendar.component';
import localeDe from '@angular/common/locales/de';
import {CustomDateFormatter} from "./Entity/CustomDateFormatter";
import { BestaetigungComponent } from './component/bestaetigung/bestaetigung.component';

registerLocaleData(localeDe);

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PractitionerComponent,
    QuestionnaireComponent,
    LoginComponent,
    RegisterComponent,
    PractitionerDetailComponent,
    ReminderDetailComponent,
    ReminderOverviewComponent,
    ReminderCreateComponent,
    InfoComponent,
    PractitionerSearchComponent,
    PractitionerCalendarComponent,
    BestaetigungComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    //Calendar
    CommonModule,
    FormsModule,
    NgbModalModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }, {
      dateFormatter: {
        provide: CalendarDateFormatter,
        useClass: CustomDateFormatter
      }
    }),
  ],
  providers: [
    {provide: LOCALE_ID, useValue: 'de'},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
