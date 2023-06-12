import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  upload(formData: FormData): Observable<HttpEvent<string[]>>{
    return this.httpClient.post<string[]>(`${this.url}/file/upload`,formData,{
      reportProgress: true,
      observe: 'events'
    })
  }

  download(filename: string): Observable<HttpEvent<Blob>>{
    return this.httpClient.get(`${this.url}/file/download/${filename}`,{
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    })
  }
}
