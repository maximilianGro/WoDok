import {Registration} from "../dto/registration";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Injectable} from "@angular/core";
import {SimpleUser} from "../dto/simple-user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private backendUrl =  environment.backendUrl;

  private registerBaseUri: string = this.backendUrl + '/users';

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Create the user. If it was successful, the user will be logged in.
   *
   * @param authRequest User data
   */

  createUser(registerRequest: Registration): Observable<Registration> {
    return this.httpClient.post<Registration>(this.registerBaseUri + '/create', registerRequest);
  }

  /**
   * Returns the user with the given e-mail address.
   *
   * @param email of the user
   */
  // get(email: string): Observable<SimpleUser> {
  //   console.log('Get user with email address ', email);
  //   return this.httpClient.get<User>(this.registerBaseUri + '/' + email);
  // }
}
