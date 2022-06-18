import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Appointment} from "../dto/appointment";
import {HttpClient} from '@angular/common/http';
import {Queue} from "../dto/queue";


const baseUri = environment.backendUrl + '/appointments';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(
    private http: HttpClient,
  ) {
  }

  getAppointmentById(id): Observable<Appointment> {
    const uri = baseUri + '/byAppointment/' + id;
    return this.http.get<Appointment>(uri);
  }

  getAppointmentsByPatientId(id): Observable<Appointment[]> {
    const uri = baseUri + '/' + id;
    return this.http.get<Appointment[]>(uri);
  }

  getFreeAppointmentsByPractitioner(id): Observable<Appointment[]> {
    const uri = baseUri + '/free/' + id;
    return this.http.get<Appointment[]>(uri);
  }

  bookAppointment(appointment: Appointment): Observable<boolean> {
    const uri = baseUri + '/free';
    return this.http.post<boolean>(uri, appointment);
  }

  addToQueue(queue: Queue): Observable<boolean> {
    const uri = baseUri + '/queue';
    return this.http.post<boolean>(uri, queue);
  }

  deleteAll(practitionerId: number) {
    const uri = baseUri + practitionerId;
    return this.http.delete<boolean>(uri);
  }
}
