import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { UserService } from 'src/app/service/user.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  hide = true; //To hide the password
  loginForm : any = FormGroup; // formulaire de login
  responseMessage:any; // la reponse du serveur
  constructor(private formBuilder:FormBuilder,
    private router:Router,
    private userService:UserService,
    public dialogRef: MatDialogRef<LoginComponent>,
    private ngxService:NgxUiLoaderService,
    private snackbarService: SnackbarService){}
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      password:[null, [Validators.required]]
    })
  }

  handleSubmit(){// Ce qui se passe quand on appuie sur le boutton login
    this.ngxService.start();
    var formData = this.loginForm.value;
    var data = {
      email: formData.email,
      password: formData.password
    }
    this.userService.login(data).subscribe({
      next:(reponse:any) =>{
        this.ngxService.stop();
        this.dialogRef.close();
        localStorage.setItem('token', reponse.token); //On sauvegarde le token généré dans localstorage
        this.router.navigate(['/home/dashboard',{

        }]);
      },
      error: (error)=>{
        this.ngxService.stop();
        if (error.error?.message) {
          this.responseMessage = error.error?.message;
        } else {
          this.responseMessage = GlobalConstants.genericError;
        }
        this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
      }
    });
  }
  forgotPassword(){

  }
}
