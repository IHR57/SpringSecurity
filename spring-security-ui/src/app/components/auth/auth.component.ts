import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { NotifierService } from '../../services/shared/notifier.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  isSignIn: boolean = true;
  isSignUp: boolean = false;
  isForgetPassword: boolean = false;
  regForm!: FormGroup;
  hidePass: boolean = true;
  hideConfirmPass: boolean = true;

  constructor(
    private authService: AuthService,
    private notifierService: NotifierService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(): void {
    this.regForm = this.formBuilder.group({
      'name': ['', Validators.required],
      'email': ['', [Validators.required, Validators.email]],
      'password': ['', Validators.required],
      'confirmPassword': ['']
    },
    {
      validator: this.matchPassword('password', 'confirmPassword'),
    })
  }

  matchPassword(password: string, confirmPassword: string) {
    return (formGroup: FormGroup) => {
      const passwordControl = formGroup.controls[password];
      const confirmPasswordControl = formGroup.controls[confirmPassword];

      if (!passwordControl || !confirmPasswordControl) {
        return null;
      }

      if (confirmPasswordControl.errors && !confirmPasswordControl.errors.passwordMismatch) {
        return null;
      }

      if (passwordControl.value !== confirmPasswordControl.value) {
        confirmPasswordControl.setErrors({ passwordMismatch: true });
      } else {
        confirmPasswordControl.setErrors(null);
      }

      return null;
    }
  }

  userNameFormControl = new FormControl('', Validators.required);
  passwordFormControl = new FormControl('', Validators.required);

  onClickLogin() {

  }

  onClickSignUp() {
    if(this.regForm.invalid)
      return;

    //this.authService.userSignUp(this.regForm.controls['email'].value, this.regForm.controls['password'].value);
  }

}
