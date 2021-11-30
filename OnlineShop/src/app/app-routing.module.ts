import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {BasketComponent} from "./basket/basket.component";
import {MarketComponent} from "./market/market.component";
import {AdminComponent} from "./admin/admin.component";
import {AuthGuService} from "./auth-gu.service";
import {CreateComponent} from "./create/create.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'create', component: CreateComponent},
  {path: 'basket', component: BasketComponent, canActivate: [AuthGuService]},
  {path: 'market', component: MarketComponent, canActivate: [AuthGuService]},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuService]},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
