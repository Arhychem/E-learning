import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CycleService } from 'src/app/service/cycle.service';
import { CycleModel } from '../model/CycleModel';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/app/service/snackbar.service';

@Component({
  selector: 'app-cycle',
  templateUrl: './cycle.component.html',
  styleUrls: ['./cycle.component.css']
})
export class CycleComponent implements OnInit {
  cycles: CycleModel[];
  data: any;
  responseMessage: any;
  constructor(private router:Router,
     private activatedRoute:ActivatedRoute,
     private cycleService:CycleService,
     private ngxService: NgxUiLoaderService,
     private snackbarService: SnackbarService){

      this.ngxService.start();
  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params=>{
      this.data = params
      this.cycleService.getAllCycleBySousSystemeId(this.data.id).subscribe({
        next:(response:any)=>{
          this.ngxService.stop();
          this.cycles = response;
          console.log(this.cycles);
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

  getAllNiveauByCycleId(id: string){
    this.router.navigate(['niveau-by-cycle',id]);
  }
}
