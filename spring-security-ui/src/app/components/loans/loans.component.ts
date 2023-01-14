import { Component, OnInit } from '@angular/core';
import { Loans } from 'src/app/model/loan.model';
import { User } from 'src/app/model/user.model';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.scss']
})
export class LoansComponent implements OnInit {

  user = new User();
  loans = new Array();
  currOutstandingBalance: number = 0;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('userdetails') || "");
    if(this.user){
      this.dashboardService.getLoansDetails(this.user.id).subscribe(
        (responseData: any) => {
        this.loans = <any> responseData.body;
        this.loans.forEach(function (this: LoansComponent, loan: Loans) {
          this.currOutstandingBalance = this.currOutstandingBalance+loan.outstandingAmount;
        }.bind(this)); 
        });
    }
  }

  

}