import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user = new User();

  constructor(
    private router: Router
  ) {}

  ngOnInit() {
    let userDetails = JSON.parse(sessionStorage.getItem('userdetails') || "{}");
    if(userDetails){
      this.user = JSON.parse(sessionStorage.getItem('userdetails') || "{}");
    }
  }

  onClickLogout() {
    window.sessionStorage.setItem("userdetails", "");
    window.sessionStorage.setItem("XSRF-TOKEN", "");
    this.router.navigate(['/login']);
  }

}
