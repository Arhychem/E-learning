import { HttpErrorResponse, HttpEvent, HttpEventType, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FileService } from 'src/app/service/file.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-upload-document',
  templateUrl: './upload-document.component.html',
  styleUrls: ['./upload-document.component.css']
})
export class UploadDocumentComponent {
  filenames: string[] = [];
  fileStatus = {
    status:'',
    requestType: '',
    percent: 0
  };
  constructor(private fileService: FileService) {
  }

  onUploadFiles(files: File[]): void {
    const formData = new FormData();
    for (const file of files) {
      formData.append('files', file, file.name);
      this.fileService.upload(formData).subscribe({
        next: (response: any) => {
          this.resportProgress(response);
        },
        error: (error: HttpErrorResponse) => console.log(error)
      });
    }
  }

  onDownloadFiles(filename: string): void {
    this.fileService.download(filename).subscribe({
      next: (response: any) => {
        this.resportProgress(response);
      },
      error: (error: HttpErrorResponse) => console.log(error)
    });
  }

  private resportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
    switch (httpEvent.type) {
      case HttpEventType.UploadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total, 'Uploading');
        break;
      case HttpEventType.DownloadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total, 'Downloading');
        break;
      case HttpEventType.ResponseHeader:
        console.log('Header returned', httpEvent);
        break;
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {
          for (const filename of httpEvent.body) {
            this.filenames.unshift(filename);
          }
        } else {
          saveAs(new File([httpEvent.body], httpEvent.headers.get('File-Name'),
            { type: `${httpEvent.headers.get('Content-Type')};charset=utf-8` }));
        }
        break;
      default:
        console.log(httpEvent);
        break;
    }
  }
  updateStatus(loaded: number, total: number, requestType: string) {
    this.fileStatus.status = 'progress';
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100* loaded / total);
  }

}
