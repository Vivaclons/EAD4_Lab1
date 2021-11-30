import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {RequestService} from "../request.service";

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  filterAuth = '';
  filterName = '';

  formAuth: FormGroup;
  formName: FormGroup;

  limit = 3;
  page = 0;

  dataTask = new MatTableDataSource();
  displayedColumns: string[] = ['id', 'name', 'category', 'description', 'price', 'action'];
  total: any = 0;

  constructor(private auth: AuthService, private service: RequestService, private formS: FormBuilder, private formI: FormBuilder) {
    this.formAuth = this.formS.group({
      category: ['']
    });

    this.formName = this.formI.group({
      name: []
    });
  }

  ngOnInit(): void {
    this.getMarket();
  }

  getMarket() {
    this.service.getOrder(this.formAuth.getRawValue()).subscribe(res => {
      this.dataTask = res;
    })
  }

  calculate(price: any){
    let total;
    total += price;
  }

  delete(id: any){
    this.service.deleteOrder(id).subscribe(res => {
      this.getMarket();
    }, error => {
      console.error(error);
    });
  }

  buy(){
    alert("Done")
  }

  logout(){
    this.auth.logout();
  }
}
