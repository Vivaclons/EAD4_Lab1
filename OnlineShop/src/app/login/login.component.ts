import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formG: FormGroup;
  constructor(private auth: AuthService, private formBuilder: FormBuilder) {
    this.formG = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      // , Validators.pattern('^[A-Za-z]')
    });
  }

  get userName(){
    return this.formG.get("userName");
  }

  get password(){
    return this.formG.get("password");
  }

  login() {
    this.auth.login(this.formG.getRawValue().userName, this.formG.getRawValue().password);
  }

  ngOnInit(): void {
  }

}
