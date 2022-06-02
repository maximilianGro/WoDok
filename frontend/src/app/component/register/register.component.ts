import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {Registration} from "../../dto/registration";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  // After first submission attempt, form validation will start
  submitted = false;
  error = false;
  errorMessage = '';


  constructor(private formBuilder: FormBuilder, private authService: AuthService, private userService: UserService, private router: Router) {
    this.registerForm = this.formBuilder.group({
      vorname: ['', [Validators.required]],
      nachname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      city: [''],
      street: [''],
      country: [''],
      zip: [''],
      geburtstag: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  ngOnInit(): void {
  }

  /**
   * Form validation will start after the method is called, additionally an AuthRequest will be sent
   */
  registerUser() {
    this.submitted = true;
    if (this.registerForm.valid) {
      // console.log(this.registerForm.controls.vorname.value)
      // console.log(this.registerForm.controls.nachname.value)
      // console.log(this.registerForm.controls.password.value)
      // const registerRequest: Registration = new Registration(
      //   this.registerForm.controls.email.value,
      //   this.registerForm.controls.password.value,
      //   false,
      //   this.registerForm.controls.vorname.value,
      //   this.registerForm.controls.nachname.value,
      //   null,
      //   null,
      //   this.registerForm.controls.geburtstag.value);
      // this.authService.registerUser(registerRequest).subscribe(
      //   () => {
      //     this.router.navigate(['/']);
      //   }
      // );
      const registerRequest = new Registration(
          this.registerForm.controls.email.value,
          this.registerForm.controls.password.value,
          false,
          this.registerForm.controls.vorname.value,
          this.registerForm.controls.nachname.value,
          this.registerForm.controls.street.value,
        this.registerForm.controls.zip.value,
        this.registerForm.controls.city.value,
        this.registerForm.controls.country.value,
          this.registerForm.controls.geburtstag.value);

      this.userService.createUser(registerRequest).subscribe({
        next: () => {
          this.authService.loginUser({
            email: registerRequest.email,
            password: registerRequest.password,
          }).subscribe({
            next: () => {
              console.log('Successfully logged in user: ' + registerRequest.email);
              this.router.navigate(['/']);
            },
            error: (error) => {
              console.log('Could not log in due to:');
              console.log(error);
              this.error = true;
              if (typeof error.error === 'object') {
                this.errorMessage = error.error.error;
              } else {
                this.errorMessage = error.error;
              }
            }
          });
        },
        error: (error) => {
          console.log('Could not log in due to:');
          console.log(error);
          this.error = true;
          if (error.status === 409) {
            this.errorMessage = 'Email already exists!';
          }
        }
      });
    } else {
      console.log('Invalid input');
    }
  }

  getErrorMessage(control) {
    if (control.hasError('required')) {
      return 'You must enter a value';
    }
    if (control.hasError('email')) {
      return 'Not a valid email';
    }
    if (control.hasError('minlength')) {
      return 'Not a valid length';
    }
    if (control.hasError('pattern')) {
      return 'Not a valid pattern';
    }
    return '';
  }

  /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
    this.errorMessage = null;
  }

}
