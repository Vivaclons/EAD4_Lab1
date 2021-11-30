import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {RequestService} from "../request.service";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {

  filterAuth = '';
  filterName = '';

  formAuth: FormGroup;
  formName: FormGroup;
  formG: FormGroup;

  limit = 3;
  page = 0;

  dataTask = new MatTableDataSource();
  displayedColumns: string[] = ['id', 'name', 'category', 'description', 'price', 'action'];
  new = false;

  constructor(private auth: AuthService, private service: RequestService, private formS: FormBuilder, private formI: FormBuilder) {
    this.formAuth = this.formS.group({
      Author: ['']
    });

    this.formName = this.formI.group({
      name: []
    });

    this.formG = this.formS.group({
      id: [''],
      name: [''],
      category: [''],
      description: [''],
      price: ['']
    });
    this.new = true;
  }

  ngOnInit(): void {
    this.getMarket();
  }

  getMarket() {
    this.service.getAll(this.formAuth.getRawValue()).subscribe(res => {
      this.dataTask = res;
    })
  }

  setFilterAndSort() {
    let str = '_page=' + this.page + '&_limit=' + this.limit;
    if (this.filterAuth !== '' && this.filterAuth !== null && this.filterAuth !== 'none') {
      str += '&Author=' + this.filterAuth;
    }
    if (this.filterName !== '' && this.filterName !== null) {
      str += '&name=' + this.filterName;
    }
    return str;
  }

  add(id: any, name: any, category: any, description: any, price: any){
    this.formG = this.formS.group({
      id: [id],
      name: [name],
      category: [category],
      description: [description],
      price: [price]
    });
    this.formG.getRawValue();
    this.service.createOrder(this.formG.getRawValue()).subscribe(result => {
      this.getMarket();
    });
    alert("Success!");
  }

  setFilterAuthor() {
    this.filterAuth = this.formAuth.getRawValue().Author;
    this.service.getAll(this.setFilterAndSort()).subscribe(res => {
      this.dataTask = res;
    })
  }

  setFilterName() {
    this.filterName = this.formName.getRawValue().name;
    this.service.getAll(this.setFilterAndSort()).subscribe( res => {
      this.dataTask = res;
    });
  }

  logout(){
    this.auth.logout();
  }

}
