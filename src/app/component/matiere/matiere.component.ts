import { Component, OnInit } from '@angular/core';
import { MatiereModel } from '../model/MatiereModel';
import { ActivatedRoute, Router } from '@angular/router';
import { NiveauService } from 'src/app/service/niveau.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { MatiereService } from 'src/app/service/matiere.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-matiere',
  templateUrl: './matiere.component.html',
  styleUrls: ['./matiere.component.css']
})
export class MatiereComponent implements OnInit{

  matieres: MatiereModel[];
  data: any;
  responseMessage: any;
  constructor(private router:Router,
     private activatedRoute:ActivatedRoute,
     private matiereService:MatiereService,
     private ngxService: NgxUiLoaderService,
     private snackbarService: SnackbarService){

  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params=>{
      this.data = params
      this.matiereService.getAllMatiereByNiveau(this.data.id).subscribe({
        next:(response:any)=>{
          this.ngxService.stop();
          this.matieres = response;
          console.log(this.matieres);
        },
        error:(error:any)=>{
          this.ngxService.stop();
          console.log(error);
          if (error.error?.message) {
            this.responseMessage = error.error?.message;
          } else {
            this.responseMessage = GlobalConstants.genericError;
          }
          this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
        }
      });
    });
  }
  getAllChapitreByMatiere(id: string){
    this.router.navigate(['chapitre-by-matiere',id]);
  }
}
