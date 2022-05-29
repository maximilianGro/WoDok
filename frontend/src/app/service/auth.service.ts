import {Injectable} from '@angular/core';
import {AuthRequest} from '../dto/auth-request';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/operators';
import jwt_decode from 'jwt-decode';
import {environment} from "../../environments/environment";
import {SimpleUser} from "../dto/simple-user";
import {Registration} from "../dto/registration";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private backendUrl =  environment.backendUrl;

  private authBaseUri: string = this.backendUrl + '/authentication';
  private registerBaseUri: string = this.backendUrl + '/register';

  private username = '';

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Login in the user. If it was successful, a valid JWT token will be stored
   *
   * @param authRequest User data
   */
  loginUser(authRequest: AuthRequest): Observable<string> {
    this.setUsername(authRequest);
    return this.httpClient.post(this.authBaseUri, authRequest, {responseType: 'text'})
      .pipe(
        tap((authResponse: string) => this.setToken(authResponse))
      );
  }

  /**
   * Register the user. If it was successful, a valid JWT token will be stored
   *
   * @param authRequest User data
   */
  registerUser(authRequest: AuthRequest): Observable<string> {
    return this.httpClient.post(this.authBaseUri, authRequest, {responseType: 'text'})
      .pipe(
        tap((authResponse: string) => this.setToken(authResponse))
      );
  }

  setUsername(authRequest: AuthRequest){
    this.username = authRequest.email;
    localStorage.setItem('username',this.username);
  }

  getusername(){
    return localStorage.getItem('username');
  }

  /**
   * Check if a valid JWT token is saved in the localStorage
   */
  isLoggedIn() {
    return !!this.getToken() && (this.getTokenExpirationDate(this.getToken()).valueOf() > new Date().valueOf());
  }

  getToken() {
    return localStorage.getItem('authToken');
  }

  getSmallUserById(username: string): Observable<SimpleUser>{
    return this.httpClient.get<SimpleUser>(environment.backendUrl + '/users' + '/' + username);
  }

  /**
   * Returns the user role based on the current token
   */
  getUserRole() {
    if (this.getToken() != null) {
      const decoded: any = jwt_decode(this.getToken());
      const authInfo: string[] = decoded.rol;
      if (authInfo.includes('ROLE_ADMIN')) {
        return 'ADMIN';
      } else if (authInfo.includes('ROLE_USER')) {
        return 'USER';
      }
    }
    return 'UNDEFINED';
  }

  private setToken(authResponse: string) {
    localStorage.setItem('authToken', authResponse);
  }

  private getTokenExpirationDate(token: string): Date {

    const decoded: any = jwt_decode(token);
    if (decoded.exp === undefined) {
      return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  logoutUser() {
    console.log('Logout');
    localStorage.removeItem('authToken');
  }

}
