import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FileUploadService } from 'src/app/service/file-upload.service';


@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.less']
})
export class UploadFileComponent implements OnInit {
  @ViewChild("fileUpload", {static: false}) fileUpload: ElementRef;files  = [];
    fileName:string;

  constructor(private fileUploadService:FileUploadService) { }


  ngOnInit() {
  }


  onClick() {
    const fileUpload = this.fileUpload.nativeElement;fileUpload.onchange = () => {
    for (let index = 0; index < fileUpload.files.length; index++)
    {
     const file = fileUpload.files[index];
      this.fileName = file.name +" is uploaded"

     this.files.push({ data: file, inProgress: false, progress: 0});
    }
      this.uploadFiles();
    };
    fileUpload.click();
}
private uploadFiles() {
  this.fileUpload.nativeElement.value = '';
  this.files.forEach(file => {
    this.uploadFile(file);
  });
}
uploadFile(file) {
  const formData = new FormData();
  formData.append('file', file.data);
  file.inProgress = true;
  this.fileUploadService.upload(formData).subscribe(
    rsp => {
      console.log(rsp.type)



},
    error => {
      console.log(error)
    });

}

}
