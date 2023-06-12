import { Component, OnInit } from '@angular/core';
import { NiveauModel } from '../model/NiveauModel';
import { ActivatedRoute, Router } from '@angular/router';
import { NiveauService } from 'src/app/service/niveau.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-niveau',
  templateUrl: './niveau.component.html',
  styleUrls: ['./niveau.component.css']
})
export class NiveauComponent implements OnInit{
  niveaux: NiveauModel[];
  data: any;
  responseMessage: any;
  constructor(private router:Router,
     private activatedRoute:ActivatedRoute,
     private niveauService:NiveauService,
     private ngxService: NgxUiLoaderService,
     private snackbarService: SnackbarService){
      this.ngxService.start();
  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params=>{
      this.data = params
      this.niveauService.getAllNiveauByCycleId(this.data.id).subscribe({
        next:(response:any)=>{
          this.ngxService.stop();
          this.niveaux = response;
          console.log(this.niveaux);
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
  getAllMatiereByNiveau(id: string){
    this.router.navigate(['matiere-by-niveau',id]);
  }

}


