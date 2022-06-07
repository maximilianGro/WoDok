import {HttpClient, HttpParams} from '@angular/common/http';
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

  /**
   * Get one practitioner by id stored in the system
   *
   * @return observable practitioner
   */
  getOne(id): Observable<Practitioner> {
    return this.http.get<Practitioner>(baseUri + "/" + id);
  }

  /**
   * Get one practitioner by id stored in the system
   *
   * @return observable practitioner
   */
  search(speciality: string, address: string, time:string): Observable<Practitioner[]> {
    let params = new HttpParams();
    params = params.append("speciality",speciality)
    params = params.append("address",address)
    params = params.append("openingHours", time)
    return this.http.get<Practitioner[]>(baseUri + "?" + params);
  }
}
