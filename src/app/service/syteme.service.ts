import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SystemeModel } from '../component/model/SystemeModele';

@Injectable({
  providedIn: 'root'
})
export class SytemeService {
  url = environment.apiUrl;
  constructor( private httpClient:HttpClient /*On definit la variable qui va porter nos requetes*/ ) {

   }
   getAllSousSystemeBySystemeParentId():Observable<SystemeModel[]>{
    return this.httpClient.get<SystemeModel[]>(this.url+"/systemeeducatif/getSousSytemeByParentId",{
      params: new HttpParams().set('systemeParentId', 'sys_01')
    });
   }


}
