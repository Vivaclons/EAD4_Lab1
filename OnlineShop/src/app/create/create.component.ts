import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {RequestService} from "../request.service";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  new = false;
  formG: FormGroup;

  constructor(private auth: AuthService, private formBuilder: FormBuilder, private service: RequestService, private router: Router) {
    this.formG = this.formBuilder.group({
      name: [''],
      category: [''],
      description: [''],
      price: ['']
    });
    this.new = true;
  }

  create() {
    this.formG.getRawValue();
    this.service.createTask(this.formG.getRawValue()).subscribe(result => {
      this.getOrder();
    });
    alert("Success!");
    this.router.navigate(["../admin"]);
  }


  getOrder() {
    this.service.getAllTasks1().subscribe(res => {
    });
  }


  ngOnInit(): void {
    this.getOrder();
  }

}
