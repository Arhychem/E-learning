import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatiereModel } from '../component/model/MatiereModel';

@Injectable({
  providedIn: 'root'
})
export class MatiereService {
  url = environment.apiUrl;

  constructor( private httpClient:HttpClient /*On definit la variable qui va porter nos requetes*/ ) {

   }
   getAllMatiereByNiveau(id: string):Observable<MatiereModel[]>{
    return this.httpClient.get<MatiereModel[]>(this.url+"/matiere/getAllByNiveauId",{
      params: new HttpParams().set('niveauId', id)
    });
   }
}
