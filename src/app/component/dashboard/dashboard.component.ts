import { AfterViewInit, Component } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { DashboardService } from 'src/app/service/dashboard.service';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { SytemeService } from 'src/app/service/syteme.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { SystemeModel } from '../model/SystemeModele';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit{
  systemes: SystemeModel[];
  responseMessage: any;
  data: any;



  ngAfterViewInit(): void {

  }
  constructor(private dashboardService: DashboardService, private ngxService: NgxUiLoaderService,
    private snackbarService: SnackbarService, private systeemService:SytemeService, private router: Router){
      this.ngxService.start();
      this.dashboardData()
    }
  dashboardData() {
    this.systeemService.getAllSousSystemeBySystemeParentId().subscribe({
      next:(response:any)=>{
        this.ngxService.stop();
        this.systemes = response;
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
    })
  }

  getAllCycleBySousSystemeId(id: string){
    this.router.navigate(['/cycle-by-sous-systeme',id]);
  }

}
