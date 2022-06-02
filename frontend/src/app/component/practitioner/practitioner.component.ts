import {Component, OnInit} from '@angular/core';
import {Practitioner} from '../../dto/practitioner';
import {PractitionerService} from 'src/app/service/practitioner.service';

@Component({
  selector: 'app-practitioner',
  templateUrl: './practitioner.component.html',
  styleUrls: ['./practitioner.component.scss']
})
export class PractitionerComponent implements OnInit {
  search = false;
  practitioners: Practitioner[];
  error: string = null;

  constructor(
    private service: PractitionerService,
  ) { }

  ngOnInit(): void {
    this.reloadPractitioners();
  }

  reloadPractitioners() {
    this.service.getAll().subscribe({
      next: data => {
        this.practitioners = data;
        console.log(data);
      },
      error: error => {
        console.error('Error fetching practitioners', error.message);
        this.showError('Could not fetch practitioners: ' + error.message);
      }
    });
  }

  public vanishError(): void {
    this.error = null;
  }

  private showError(msg: string) {
    this.error = msg;
  }
}
