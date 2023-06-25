import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { LeconService } from 'src/app/service/lecon.service';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { LeconModel } from '../model/LeconModel';
import { AddLeconComponent } from '../add-lecon/add-lecon.component';
import { MAT_DIALOG_DATA, MatDialog, MatDialogConfig } from '@angular/material/dialog';

import { Inject } from '@angular/core';
import { UploadFileComponent } from '../upload-file/upload-file.component';

@Component({
  selector: 'app-lecon',
  templateUrl: './lecon.component.html',
  styleUrls: ['./lecon.component.css']
})
export class LeconComponent implements OnInit {

  lecons: LeconModel[];
  data: any;
  responseMessage: any;
  chapitreId: any;
  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    private leconService: LeconService,
    private ngxService: NgxUiLoaderService,
    private snackbarService: SnackbarService,
    private dialog: MatDialog,
  ) {

    this.ngxService.start();
  }
  ngOnInit(): void {
    this.getAllLeconByChapitre();

  }

  addDocument() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "850px";
    this.dialog.open(UploadFileComponent, dialogConfig)
  }
  getAllLeconByChapitre() {
    this.activatedRoute.params.subscribe(params => {
      this.data = params
      this.chapitreId = this.data.id;
      this.leconService.getAllLeconByChapitre(this.data.id).subscribe({
        next: (response: any) => {
          this.ngxService.stop();
          this.lecons = response;
          console.log(this.lecons);
        },
        error: (error: any) => {
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

  handleAddAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    dialogConfig.data = this.chapitreId;
    this.dialog.open(AddLeconComponent, dialogConfig).afterClosed().subscribe({
      next: (response: any) => {
        this.getAllLeconByChapitre();
      }
    });
  }

  handleModifyAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    dialogConfig.data = {
      chapitreId: this.chapitreId,
      isUpdate: true
    }
    this.dialog.open(AddLeconComponent, dialogConfig).afterClosed().subscribe({
      next: (response: any) => {
        this.getAllLeconByChapitre();
      }
    });
  }

  deleteLecon(leconId: string) {
    this.leconService.deleteLecon(leconId).subscribe({
      next: (reponse:any) =>{
        console.log(reponse)
      },error: error => console.log(error)
    });
  }
}
