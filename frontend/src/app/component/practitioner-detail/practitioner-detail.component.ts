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

  constructor(private route: ActivatedRoute, private practitionerService: PractitionerService, private authService: AuthService,
              private appointmentService: AppointmentService) {
  }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.practitionerService.getOne(id).subscribe({
      next: data => {
        this.practitioner = data;
        console.log(data);
      },
      error: error => {
        console.error('Error fetching practitioner', error.message);
        //this.showError('Could not fetch practitioner: ' + error.message);
      }
    });
  }
}
