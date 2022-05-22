import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {Registration} from "../../dto/registration";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  // After first submission attempt, form validation will start
  submitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.formBuilder.group({
      vorname: ['', [Validators.required]],
      nachname: ['', [Validators.required]],
      email: ['', [Validators.required]],
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
      console.log(this.registerForm.controls.vorname.value)
      console.log(this.registerForm.controls.password.value)
      const registerRequest: Registration = new Registration(
        this.registerForm.controls.email.value,
        this.registerForm.controls.password.value,
        false,
        this.registerForm.controls.vorname.value,
        this.registerForm.controls.nachname.value,
        null,
        null,
        this.registerForm.controls.password.value);
      this.authService.registerUser(registerRequest).subscribe(
        () => {
          this.router.navigate(['/']);
        }
      );
    } else {
      console.log('Invalid input');
    }
  }

}
