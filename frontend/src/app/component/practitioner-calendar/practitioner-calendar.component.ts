import {ChangeDetectionStrategy, Component, OnInit, ViewChild,} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {isSameDay, isSameMonth,} from 'date-fns';
import {CalendarEvent, CalendarView, DAYS_OF_WEEK,} from 'angular-calendar';
import {AppointmentService} from "../../service/appointment.service";
import {Appointment} from "../../dto/appointment";
import {Practitioner} from "../../dto/practitioner";
import {PractitionerService} from "../../service/practitioner.service";
import {MyEvent} from '../../entity/MyEvent';
import {UserService} from "../../service/user.service";
import {Queue} from "../../dto/queue";

@Component({
  selector: 'app-practitioner-calendar',
  templateUrl: './practitioner-calendar.component.html',
  styleUrls: ['./practitioner-calendar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class PractitionerCalendarComponent implements OnInit {
  activeDayIsOpen = true;
  booked = false;
  book = false;
  view: CalendarView = CalendarView.Month;
  weekStartsOn = DAYS_OF_WEEK.MONDAY;
  calendarView = CalendarView;
  viewDate: Date = new Date();
  practitioner: Practitioner;
  events: MyEvent[] = [];
  freeAppointments: Appointment[];
  appointmentToBook: Appointment;
  userId: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private appointmentService: AppointmentService,
              private practitionerService: PractitionerService,
              private userService: UserService
  ) {
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.loadPractitioner(id);
    this.loadFreeAppointments(id);
    this.loadUserId();
  }


  dayClicked({date, events}: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  changeWeek(date: Date) {
    this.viewDate = date;
    this.view = CalendarView.Week;
  }

  loadPractitioner(id) {
    this.practitionerService.getOne(id).subscribe({
      next: data => {
        this.practitioner = data;
      }
    });
  }

  loadFreeAppointments(id) {
    this.appointmentService.getFreeAppointmentsByPractitioner(id).subscribe({
      next: data => {
        this.freeAppointments = data;
        this.addFreeAppointments(data);
      }
    });
  }

  addFreeAppointments(freeAppointments: Appointment[]) {
    this.events = new Array<MyEvent>(freeAppointments.length);
    for (let i = 0; i < freeAppointments.length; i++) {
      const freeAppointment = freeAppointments[i];
      const start = new Date(freeAppointment.start);
      const end = new Date(freeAppointment.end);
      const startString = start.getHours() + ':' + (start.getMinutes() < 10 ? '0' : '') + start.getMinutes();
      const endString = end.getHours() + ':' + (end.getMinutes() < 10 ? '0' : '') + end.getMinutes();
      this.events[i] = {
        title: startString + ' - ' + endString,
        start,
        end,
        color: {
          primary: '#ACE1AF',
          secondary: '#e3fbe3',
        },
        index: i
      };
    }
  }

  createNewAppointment(event: MyEvent) {
    this.book = true;
    this.appointmentToBook = this.freeAppointments[event.index];
    this.appointmentToBook.patientId = this.userId;
  }

  bookAppointment() {
    this.booked = true;
    this.appointmentToBook.freeAppointment = false;
    this.appointmentService.bookAppointment(this.appointmentToBook).subscribe();
  }

  close() {
    this.book = false;
    this.appointmentToBook = null;
  }

  warteschlange() {
    const queue: Queue = {
      userId: this.userId, practitionerId: this.practitioner.id
    };
    console.log(queue);
    this.appointmentService.addToQueue(queue).subscribe({
      next: () => {
        window.alert('Erfolgreich in die Warteschlange hinzugefügt');
        this.router.navigate(['/practitioners']);
      },
      // eslint-disable-next-line @typescript-eslint/no-shadow
      error: (error) => {
        window.alert('Fehler bei Hinzufügen in die Warteschlange ' + error.error.message);
      }
    });
  }

  loadUserId() {
    this.userService.getUserId(window.localStorage.getItem('username')).subscribe({
      next: id => {
        this.userId = id;
        console.log(id);
      }
    });
  }
}

