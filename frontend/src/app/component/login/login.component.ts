import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthRequest} from "../../dto/auth-request";
import {AuthService} from "../../service/auth.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  // Counts the number of wrong tries for the password
  passwordCounter = 0;

  error = false;
  errorMessage = '';

  // Display error message if the login was not successful
  wrongLogin = false;

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
    console.log('Try to authenticate user: ' + authRequest.email);
    this.authService.loginUser(authRequest).subscribe({
        next: () => {
          // Resetting the number of wrong passwords
          this.passwordCounter = 0;
          this.wrongLogin = false;

          console.log('Successfully logged in user: ' + authRequest.email);
          this.router.navigate(['/']);
        },
        error: (error) => {
          // Hide error message in the form
          this.wrongLogin = true;

          // todo
          // // Load the number of wrong password tries for the user
          // this.userService.get(this.loginForm.controls.username.value).subscribe({
          //   next: (user: User) => {
          //     this.passwordCounter = user.lockedCounter;
          //   },
          //   error: (err) => {
          //     console.log(err);
          //   }
          // });

          console.log('Could not log in due to:');
          console.log(error);
          this.error = true;
          this.errorMessage = 'Bad credentials';
        }
      }
    );
  }

  /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
  }

  getErrorMessage() {
    if (this.loginForm.controls.username.hasError('required')) {
      return 'You must enter a value';
    }
    return this.loginForm.controls.username.hasError('email') ? 'Not a valid email' : '';
  }


  ngOnInit(): void {
  }

}
