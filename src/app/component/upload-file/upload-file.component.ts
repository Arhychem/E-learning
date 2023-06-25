import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FileService } from 'src/app/service/file.service';
import { FileModel } from '../model/FileModel';



@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {
  filesInDataBase: FileModel[];
  itemToUpdate:any;

  lire = true;
  videoUrl = "";

  constructor(private fileService: FileService ) { }

  ngOnInit() {
  }

files: File[] = [];
onSelect(event:any) {
  this.files.push(...event.addedFiles);
  console.log(this.files[this.files.length -1].name);
  this.fileService.copyFiles(this.files[this.files.length -1].name).subscribe({
    next: (response:any)=>{
      this.videoUrl = "/assets/"+this.files[this.files.length -1].name;
    },
    error: (err:any)=>console.log(err)
  })
  console.log(this.files);

}

onRemove(event:any) {
  console.log(event);
  this.files.splice(this.files.indexOf(event), 1);
}

clickFileInput(fileInput:any, item:any){
  this.itemToUpdate = item;
  fileInput.click()
}
onFileChange(event:any){
  const file: File = event.target.files[0];
  console.log(file);
  let formData = new FormData();
  formData.append("name", this.itemToUpdate.name);
  formData.append("file", file);
  formData.append("id", "idTest");
  this.addFile(formData)

}
addFile(data:FormData){
  this.fileService.addDocument(data).subscribe({
    next:(reponse:any)=>{
      this.itemToUpdate.value = reponse.message;
    },
    error: error=>console.log()
  })
}
// openModal(content:any){
//   this.modalService.open(content, {centered: true});
// }
// openElementsModal(content: any, cat: string){
//   this.openModal(content);
// }

}
