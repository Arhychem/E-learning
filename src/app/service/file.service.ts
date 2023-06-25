import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';
import { FileModel } from '../component/model/FileModel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  url = environment.apiUrl;


  constructor(private httpClient: HttpClient) { }
  getAllFilesByLeconId(leconId:string):Observable<FileModel[]>{
    return this.httpClient.get<FileModel[]>(this.url+"/file/getAllByLeconId",{
      params: new HttpParams().set('leconId', leconId)
    })
  }

  viewFiles(fileLocation:string){
    return this.httpClient.get(this.url+"/file/viewFiles",{
      params: new HttpParams().set('fileLocation', fileLocation)
    });
  }addDocument(data:any){
    return this.httpClient.post(this.url+"/file/addDocument", data);
  }

  getFileName(file:any){
    return this.httpClient.get(this.url + "/files/getFilesName",{
      params: new HttpParams().set('file', file)
    })
  }

  copyFiles(fileName:any){
    return this.httpClient.post(this.url +"/files/copyFiles", fileName);
  }

}
