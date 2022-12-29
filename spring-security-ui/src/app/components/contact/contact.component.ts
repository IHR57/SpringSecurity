import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Contact } from 'src/app/model/contact.model';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  model = new Contact();

  constructor(private dashboardService: DashboardService) {

  }

  ngOnInit() {
  }

  saveMessage(contactForm: NgForm) {
    this.dashboardService.saveMessage(this.model)
    .subscribe(
      (data: any) => {
        this.model = <any> data.body;
        contactForm.resetForm();
      }, 
      (error: any) => {
        console.log(error);
      });
  }
}
