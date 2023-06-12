import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SystemeModel } from '../component/model/SystemeModele';
import { Observable } from 'rxjs';
import { CycleModel } from '../component/model/CycleModel';

@Injectable({
  providedIn: 'root'
})
export class CycleService {
  url = environment.apiUrl;

  constructor( private httpClient:HttpClient /*On definit la variable qui va porter nos requetes*/ ) {

   }
   getAllCycleBySousSystemeId(id: string):Observable<CycleModel[]>{
    return this.httpClient.get<CycleModel[]>(this.url+"/cycle/getAllBySousSystemeId",{
      params: new HttpParams().set('sousSystemeId', id)
    });
   }
}
