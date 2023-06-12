import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UserModel } from '../component/model/UserModel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // premierement on donne l'url de notre api
  url = environment.apiUrl;
  constructor( private httpClient:HttpClient /*On definit la variable qui va porter nos requetes*/ ) {

   }
   signUp(data: any){
    return this.httpClient.post(this.url+"/user/signup", data, {
      headers: new HttpHeaders().set('content-Type', 'application/json')
    })
   }

   login(data: any){
    return this.httpClient.post(this.url+"/user/login", data, {
      headers: new HttpHeaders().set('content-Type', 'application/json')
    })
   }

   checkToken(){
    return this.httpClient.get(this.url+"/user/checkToken");
   }
   getUserByEmail(email: string):any{
      return this.httpClient.get(this.url+"/user/getUserByEmail",{
      params: new HttpParams().set('userEmail',email)
    });
   }
}
