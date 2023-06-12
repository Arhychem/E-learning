import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../environment/environment';
import { NiveauModel } from '../component/model/NiveauModel';

@Injectable({
  providedIn: 'root'
})
export class NiveauService {

  url = environment.apiUrl;

  constructor( private httpClient:HttpClient /*On definit la variable qui va porter nos requetes*/ ) {

   }
   getAllNiveauByCycleId(id: string):Observable<NiveauModel[]>{
    return this.httpClient.get<NiveauModel[]>(this.url+"/niveau/getAllByCycleId",{
      params: new HttpParams().set('cycleId', id)
    });
   }
}
