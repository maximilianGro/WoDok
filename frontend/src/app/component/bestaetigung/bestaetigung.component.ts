import { Component, OnInit } from '@angular/core';
import {AppointmentService} from "../../service/appointment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Appointment} from "../../dto/appointment";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-bestaetigung',
  templateUrl: './bestaetigung.component.html',
  styleUrls: ['./bestaetigung.component.scss']
})
export class BestaetigungComponent implements OnInit {
  appointment: Appointment;

  constructor(private route: ActivatedRoute, private router: Router, private appointmentService: AppointmentService, private authService: AuthService) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.appointmentService.getAppointmentById(id).subscribe({
      next: data => {
        this.appointment = data;
        console.log(data);
        this.formatDate();
      },
      error: error => {
        console.error('Error fetching appointment', error.message);
        //this.showError('Could not fetch practitioner: ' + error.message);
      }
    });
  }

  formatDate(): void {
    this.appointment.start = new Date(this.appointment.start);
    this.appointment.end = new Date(this.appointment.end);
  }

  bestaetigung() {
    if (this.appointment != null) {
      this.appointmentService.bookAppointment(this.appointment).subscribe({
        next: () => {
          window.alert('Der Termin wurde gebucht');
          if (this.authService.isLoggedIn()){
            this.router.navigate(['/termin-uebersicht']);
          } else {
            this.router.navigate(['/login']);
          }
        },
        error: error => {
          console.error('Termin konnte nicht gebucht werden', error.message);
          //this.showError('Could not fetch practitioner: ' + error.message);
        }
      });
    } else {
      console.error('Fehler');
    }
  }

}
