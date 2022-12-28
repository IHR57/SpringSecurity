import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/services/dashboard.service';
import { NotifierService } from 'src/app/services/shared/notifier.service';

@Component({
  selector: 'app-notices',
  templateUrl: './notices.component.html',
  styleUrls: ['./notices.component.scss']
})
export class NoticesComponent implements OnInit {

  notices = new Array();

  constructor(
    private dashboardService: DashboardService,
    private notifierService: NotifierService
    ) { }

  ngOnInit(): void {
    this.dashboardService.getNoticeDetails().subscribe(
      (data : any) => {
        console.log(data);
        this.notices = data.body; 
      }, 
      (error:any) => {
        console.log(error);
        this.notifierService.showNotification(error.error, 'error');
      });
  }

}
