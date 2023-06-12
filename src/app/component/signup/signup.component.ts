import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { UserService } from 'src/app/service/user.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
  password = true;
  confirmPassword = true;
  signupForm: any = FormGroup;
  reponseMessage: any;

  constructor(
    private formBuilder: FormBuilder,
    private router:Router,
    private userService:UserService,
    private snackBarService: SnackbarService,
    public dialogRef: MatDialogRef<SignupComponent>,
    private ngxService: NgxUiLoaderService
  ){

  }
  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      name:[null,[Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      contactNumber: [null, [Validators.required, Validators.pattern(GlobalConstants.contactNumberRegex)]],
      password:[null, [Validators.required]],
      confirmPassword:[null, [Validators.required]],
    })
  }
  validateSubmit(){
    if (this.signupForm.controls['password'].value != this.signupForm.controls['confirmPassword'].value) {
      return true;
    } else {
      return false;
    }
  }

  handleSubmit(){ //Lorsque l'on clique sur cette methode on recupere les données entrées dans le formulaire dans data
    this.ngxService.start();
    var formData = this.signupForm.value;
    var data = {
      name : formData.name,
      email: formData.email,
      contactNumber: formData.contactNumber,
      password: formData.password
    }
//On appelle la methode signUp dans le service et on lui passe data
    this.userService.signUp(data).subscribe({
      next:(reponse: any) =>{//S'il n'y a pas d'erreur
      this.ngxService.stop();
      this.dialogRef.close();
      this.reponseMessage = reponse?.message;
      this.snackBarService.openSnackBar(this.reponseMessage, "");
      this.router.navigate(['/']);
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
