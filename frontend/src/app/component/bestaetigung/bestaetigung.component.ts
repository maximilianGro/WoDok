import { Component, OnInit } from '@angular/core';
import {AppointmentService} from "../../service/appointment.service";
import {ActivatedRoute} from "@angular/router";
import {Appointment} from "../../dto/appointment";

@Component({
  selector: 'app-bestaetigung',
  templateUrl: './bestaetigung.component.html',
  styleUrls: ['./bestaetigung.component.scss']
})
export class BestaetigungComponent implements OnInit {
  appointment: Appointment;

  constructor(private route: ActivatedRoute, private appointmentService: AppointmentService) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.appointmentService.getAppointmentById(id).subscribe({
      next: data => {
        this.appointment = data;
        console.log(data);
      },
      error: error => {
        console.error('Error fetching appointment', error.message);
        //this.showError('Could not fetch practitioner: ' + error.message);
      }
    });
  }

  bestaetigung() {
    if (this.appointment != null) {
      this.appointmentService.bookAppointment(this.appointment)
    } else {
      console.error('Fehler');
    }
  }

}
