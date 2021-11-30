import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {RequestService} from "../request.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  filterAuth = '';
  filterName = '';

  formAuth: FormGroup;
  formName: FormGroup;

  limit = 3;
  page = 0;

  dataTask = new MatTableDataSource();
  displayedColumns: string[] = ['id', 'name', 'category', 'description', 'price', 'action'];

  constructor(private auth: AuthService, private service: RequestService, private formS: FormBuilder, private formI: FormBuilder, private router: Router) {
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
    this.service.getAll(this.formAuth.getRawValue()).subscribe(res => {
      this.dataTask = res;
    })
  }

  setFilterAndSort() {
    let str = '_page=' + this.page + '&_limit=' + this.limit;
    if (this.filterAuth !== '' && this.filterAuth !== null && this.filterAuth !== 'none') {
      str += '&category=' + this.filterAuth;
    }
    if (this.filterName !== '' && this.filterName !== null) {
      str += '&name=' + this.filterName;
    }
    return str;
  }

  setFilterAuthor() {
    this.filterAuth = this.formAuth.getRawValue().category;
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

  delete(id: any){
    this.service.deleteTask(id).subscribe(res => {
      this.getMarket();
    }, error => {
      console.error(error);
    });
  }

  add(){
    this.router.navigate(["../create"])
  }

  logout(){
    this.auth.logout();
  }

}
