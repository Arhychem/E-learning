import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { UserService } from 'src/app/service/user.service';
import { SignupComponent } from '../signup/signup.component';
import { LeconService } from 'src/app/service/lecon.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-add-lecon',
  templateUrl: './add-lecon.component.html',
  styleUrls: ['./add-lecon.component.css']
})
export class AddLeconComponent implements OnInit{

  addLeconForm: any = FormGroup;
  reponseMessage: any;
  constructor(
    private formBuilder: FormBuilder,
    private router:Router,
    private leconService:LeconService,
    private snackBarService: SnackbarService,
    public dialogRef: MatDialogRef<SignupComponent>,
    private ngxService: NgxUiLoaderService,
    @Inject(MAT_DIALOG_DATA) public chapitreId: any
  ){

  }
  ngOnInit(): void {
    this.addLeconForm = this.formBuilder.group({
      leconName:[null,[Validators.required, Validators.pattern(GlobalConstants.nameRegex)]]
    })
  }



  handleSubmit(){ //Lorsque l'on clique sur cette methode on recupere les données entrées dans le formulaire dans data
    this.ngxService.start();
    var formData = this.addLeconForm.value;
    var data = {
      leconName : formData.leconName,
      chapitreId: this.chapitreId
    }
    this.leconService.addNewLecon(data).subscribe({
      next:(reponse: any) =>{//S'il n'y a pas d'erreur
      this.ngxService.stop();
      this.dialogRef.close();
      this.reponseMessage = reponse?.message;
      this.snackBarService.openSnackBar(this.reponseMessage, "");

    }, error: error =>{ //S'il y'a erreur
      this.ngxService.stop();
      if (error.error?.message) {
        this.reponseMessage = error.error?.message;
      } else {
        this.reponseMessage = GlobalConstants.genericError;
      }
      this.snackBarService.openSnackBar(this.reponseMessage, GlobalConstants.error);
    }});
  }
}
