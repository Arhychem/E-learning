import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ChapitreModel } from '../component/model/ChapitreModel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChapitreService {
  url = environment.apiUrl;

  constructor( private httpClient:HttpClient /*On definit la variable qui va porter nos requetes*/ ) {

   }
   getAllChapitreByMatiere(id: string):Observable<ChapitreModel[]>{
    return this.httpClient.get<ChapitreModel[]>(this.url+"/chapitre/getAllByMatiereId",{
      params: new HttpParams().set('matiereId', id)
    });
   }
}
