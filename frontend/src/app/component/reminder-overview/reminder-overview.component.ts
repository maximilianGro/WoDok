import {Component, OnInit} from '@angular/core';
import {AppointmentService} from "../../service/appointment.service";
import {Appointment} from "../../dto/appointment";
import {Practitioner} from "../../dto/practitioner";
import {PractitionerService} from "../../service/practitioner.service";
import {UserService} from "../../service/user.service";


@Component({
  selector: 'app-reminder-overview',
  templateUrl: './reminder-overview.component.html',
  styleUrls: ['./reminder-overview.component.scss']
})
export class ReminderOverviewComponent implements OnInit {
  userId: number;
  appointments: Appointment[];
  loading = true;
  noAppointments;
  practitioners: Practitioner[] = [];

  constructor(private appointmentService: AppointmentService,
              private practitionerService: PractitionerService,
              private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.loadUserId();
  }

  loadAppointments() {
    this.appointmentService.getAppointmentsByPatientId(this.userId).subscribe({
      next: appointments => {
        this.noAppointments = false;
        this.loading = false;
        this.appointments = appointments;
        this.loadPractitioners();
      },
      error: error => {
        if (error.status === 404) {
          this.loading = false;
          this.noAppointments = true;
        }
      }
    });
  }

  loadPractitioners() {
    for (let i = 0; i < this.appointments.length; i++) {
      let practitionerAlreadyLoaded = false;
      for (let j = 0; j < this.practitioners.length; j++) {
        if (this.appointments[i].practitionerId === this.practitioners[j].id) {
          this.practitioners.push(this.practitioners[j]);
          practitionerAlreadyLoaded = true;
          break;
        }
      }
      if (!practitionerAlreadyLoaded) {
        this.practitionerService.getOne(this.appointments[i].practitionerId).subscribe({
          next: practitioner => {
            this.practitioners.push(practitioner);
          }
        });
      }
    }
  }

  loadUserId() {
    this.userService.getUserId(window.localStorage.getItem('username')).subscribe({
      next: id => {
        this.userId = id;
        console.log(id);
        this.loadAppointments();
      }
    });
  }
}
