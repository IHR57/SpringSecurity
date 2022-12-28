import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { AngularFireAuth } from '@angular/fire/auth';
import { auth } from 'firebase/app';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    public auth: AngularFireAuth
  ) { }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  async userLogin(userName: string, password: string) {

    // let request = Object.assign({});
    // request.userName = userName;
    // request.password = password;

    // const url = `http://localhost:8080/auth/login`;

    // return this.http.post<Object>(url, request)
    // .pipe(
    //   retry(0),
    //   catchError(this.handleError)
    // );
    await this.auth.signInWithEmailAndPassword(userName, password)
    .then((res) => {
      console.log(res.user);
      localStorage.setItem("user", JSON.stringify(res.user))
    })
  }

  userSignUp(email: string, password: string) {
    this.auth.createUserWithEmailAndPassword(email, password);
  }

  private handleError(err: HttpErrorResponse) {
    let msg: string;
    if(err.error instanceof ErrorEvent) {
      msg = `An Error Occured: ${err.error.message}`;
    }
    else {
      msg = `An Error Occured: ${err.error.message}`;
    }

    return throwError(msg);
  }
}
