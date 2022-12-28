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
    private http: HttpClient
  ) { }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

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
