import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthRequest} from "../../dto/auth-request";
import {SimpleUser} from "../../dto/simple-user";
import {AuthService} from "../../service/auth.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  // After first submission attempt, form validation will start
  submitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  /**
   * Form validation will start after the method is called, additionally an AuthRequest will be sent
   */
  loginUser() {
    this.submitted = true;
    if (this.loginForm.valid) {
      console.log(this.loginForm.controls.username.value)
      console.log(this.loginForm.controls.password.value)
      const authRequest: AuthRequest = new AuthRequest(this.loginForm.controls.username.value, this.loginForm.controls.password.value);
      this.authenticateUser(authRequest);
    } else {
      console.log('Invalid input');
    }
  }

  /**
   * Send authentication data to the authService. If the authentication was successfully, the user will be forwarded to the message page
   *
   * @param authRequest authentication data from the user login form
   */
  authenticateUser(authRequest: AuthRequest) {
    this.authService.loginUser(authRequest).subscribe(
      () => {
        this.router.navigate(['/']);
        this.authService.getSmallUserById(authRequest.username).subscribe(
          (user: SimpleUser) => {
            console.log("authenticate User")
          }
        );
      },
      error => {
        console.log('Could not log in due to:');
      }
    );
  }


  ngOnInit(): void {
  }

}
