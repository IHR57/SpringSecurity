import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NotifierComponent } from 'src/app/shared/notifier/notifier.component';

@Injectable({
  providedIn: 'root'
})
export class NotifierService {

  constructor(private snackBar: MatSnackBar) { }

  showNotification(msg: string, type: string) {
    this.snackBar.openFromComponent(NotifierComponent, {
      data: {
        message: msg
      },
      duration: 5000,
      horizontalPosition: 'end',
      verticalPosition: 'bottom',
      panelClass: type
    })
  }
}
