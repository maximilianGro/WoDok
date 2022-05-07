import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {Practitioner} from '../dto/practitioner';

const baseUri = environment.backendUrl + '/practitioners';

@Injectable({
  providedIn: 'root'
})
export class PractitionerService {

  constructor(
    private http: HttpClient,
  ) { }

  /**
   * Get all practitioners stored in the system
   *
   * @return observable list of found practitioners.
   */
  getAll(): Observable<Practitioner[]> {
    return this.http.get<Practitioner[]>(baseUri);
  }
}
