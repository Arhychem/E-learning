import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChapitreModel } from '../component/model/ChapitreModel';
import { LeconModel } from '../component/model/LeconModel';

@Injectable({
  providedIn: 'root'
})
export class LeconService {
  url = environment.apiUrl;

  constructor( private httpClient:HttpClient /*On definit la variable qui va porter nos requetes*/ ) {

   }
   getAllLeconByChapitre(id: string):Observable<LeconModel[]>{
    return this.httpClient.get<LeconModel[]>(this.url+"/lecon/getAllByChapitreId",{
      params: new HttpParams().set('chapitreId', id)
    });
   }

   addNewLecon(data: any){
    return this.httpClient.post(this.url+"/lecon/add", data, {
      headers: new HttpHeaders().set('content-Type', 'application/json')
    })
   }
}
