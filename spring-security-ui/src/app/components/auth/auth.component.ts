import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
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
  model = new User();

  constructor(
    private authService: AuthService,
    private notifierService: NotifierService,
    private formBuilder: FormBuilder,
    private router: Router
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

  validateUser() {
    if(!this.userNameFormControl.valid || !this.passwordFormControl.valid)
      return;
    this.model.email = this.userNameFormControl.value;
    this.model.password = this.passwordFormControl.value;
    this.model.name = this.userNameFormControl.value;
    this.authService.validateLoginDetails(this.model).subscribe(
      responseData => {
        window.sessionStorage.setItem("Authorization", responseData.headers.get('Authorization')!);
        this.model = <any> responseData.body;
        this.model.authStatus = 'AUTH';
        window.sessionStorage.setItem("userdetails",JSON.stringify(this.model));
        let xsrf = this.getCookie('XSRF-TOKEN');
        window.sessionStorage.setItem("XSRF-TOKEN",xsrf);
        this.router.navigate(['notices']);
      }, error => {
        console.log(error);
      });

  }

  getCookie(name: string) {
    let cookie: any = {};
    console.log(document.cookie);
    document.cookie.split(';').forEach(function(el) {
      let [k,v] = el.split('=');
      cookie[k.trim()] = v;
    })
    return cookie[name];
  }

}
