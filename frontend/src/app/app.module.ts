import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './component/header/header.component';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { PractitionerComponent } from './component/practitioner/practitioner.component';
import { QuestionnaireComponent } from './component/questionnaire/questionnaire.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { PractitionerDetailComponent } from './component/practitioner-detail/practitioner-detail.component';
import { ReminderDetailComponent } from './component/reminder-detail/reminder-detail.component';
import { ReminderOverviewComponent } from './component/reminder-overview/reminder-overview.component';
import { ReminderCreateComponent } from './component/reminder-create/reminder-create.component';
import { InfoComponent } from './component/info/info.component';
import { PractitionerSearchComponent } from './component/practitioner-search/practitioner-search.component';

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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
