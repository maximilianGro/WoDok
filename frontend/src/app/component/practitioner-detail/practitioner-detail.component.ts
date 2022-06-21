import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PractitionerService} from "../../service/practitioner.service";
import {Practitioner} from "../../dto/practitioner";
import {AuthService} from "../../service/auth.service";
import {AppointmentService} from "../../service/appointment.service";


@Component({
  selector: 'app-practitioner-detail',
  templateUrl: './practitioner-detail.component.html',
  styleUrls: ['./practitioner-detail.component.scss']
})
export class PractitionerDetailComponent implements OnInit {

  practitioner: Practitioner;
  linkToCalendar = '/practitioners/detail/';

  constructor(private route: ActivatedRoute, private practitionerService: PractitionerService, private authService: AuthService,
              private appointmentService: AppointmentService) {
  }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.practitionerService.getOne(id).subscribe({
      next: data => {
        this.practitioner = data;
        this.linkToCalendar = this.linkToCalendar + data.id;
      },
      error: error => {
        console.error('Error fetching practitioner', error.message);
        //this.showError('Could not fetch practitioner: ' + error.message);
      }
    });
  }

  delete() {
    this.appointmentService.deleteAll(this.practitioner.id).subscribe({
      next: () => {
        window.alert('Successfully deleted the appointment');
      },
      error: (error) => {
        window.alert('Error during deleting appointment: ' + error.error.message);
      }
    });
  }
}
