import { Component, OnInit } from '@angular/core';
import { ChapitreModel } from '../model/ChapitreModel';
import { ActivatedRoute, Router } from '@angular/router';
import { MatiereService } from 'src/app/service/matiere.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { ChapitreService } from 'src/app/service/chapitre.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddLeconComponent } from '../add-lecon/add-lecon.component';
import { AddChapterComponent } from '../add-chapter/add-chapter.component';


@Component({
  selector: 'app-chapitre',
  templateUrl: './chapitre.component.html',
  styleUrls: ['./chapitre.component.css']
})
export class ChapitreComponent implements OnInit{
  chapitres: ChapitreModel[];
  data: any;
  matiereId: any;
  responseMessage: any;
  constructor(private router:Router,
     private activatedRoute:ActivatedRoute,
     private chapitreService:ChapitreService,
     private ngxService: NgxUiLoaderService,
     private snackbarService: SnackbarService,
     private dialog: MatDialog){

      this.ngxService.start();
  }
  ngOnInit(): void {
    this.getAllChapitreByMatiere();

  }

  getAllLeconByChapitre(id: string){
    this.router.navigate(['lecon-by-chapitre',id]);
  }
  getAllChapitreByMatiere(){
    this.activatedRoute.params.subscribe(params=>{
      this.data = params;
      this.matiereId = this.data.id;
      this.chapitreService.getAllChapitreByMatiere(this.data.id).subscribe({
        next:(response:any)=>{
          this.ngxService.stop();
          this.chapitres = response;
          this.matiereId = this.chapitres[0].matiereId;
          console.log(this.chapitres);
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
  handleAddAction(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    dialogConfig.data = this.matiereId;
    this.dialog.open(AddChapterComponent, dialogConfig).afterClosed().subscribe({
      next: (response: any) => {
        this.getAllChapitreByMatiere();
      }
    });
  }
}
