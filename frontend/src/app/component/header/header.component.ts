import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {AppointmentService} from "../../service/appointment.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isCollapsed = document.body.offsetWidth < 992;

  constructor(public authService: AuthService, public appointmentService: AppointmentService) {
  }

  ngOnInit() {
  }

}
