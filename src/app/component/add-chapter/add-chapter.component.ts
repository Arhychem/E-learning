import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { LeconService } from 'src/app/service/lecon.service';
import { SnackbarService } from 'src/app/service/snackbar.service';
import { SignupComponent } from '../signup/signup.component';
import { ChapitreService } from 'src/app/service/chapitre.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-add-chapter',
  templateUrl: './add-chapter.component.html',
  styleUrls: ['./add-chapter.component.css']
})
export class AddChapterComponent implements OnInit {

  addChapterForm: any = FormGroup;
  reponseMessage: any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private chapitreService: ChapitreService,
    private snackBarService: SnackbarService,
    public dialogRef: MatDialogRef<SignupComponent>,
    private ngxService: NgxUiLoaderService,
    @Inject(MAT_DIALOG_DATA) public matiereId: any
  ) {

  }
  ngOnInit(): void {
    this.addChapterForm = this.formBuilder.group({
      chapitreName: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]]
    })
  }
  handleSubmit() {
    this.ngxService.start();
    var formData = this.addChapterForm.value;
    var data = {
      chapitreName: formData.chapitreName,
      matiereId: this.matiereId
    }
    console.log(data);
    this.chapitreService.addNewChapter(data).subscribe({
      next: (reponse: any) => {//S'il n'y a pas d'erreur
        this.ngxService.stop();
        // this.dialogRef.close();
        this.reponseMessage = reponse?.message;
        this.snackBarService.openSnackBar(this.reponseMessage, "");

      }, error: error => { //S'il y'a erreur
        this.ngxService.stop();
        if (error.error?.message) {
          this.reponseMessage = error.error?.message;
        } else {
          this.reponseMessage = GlobalConstants.genericError;
        }
        this.snackBarService.openSnackBar(this.reponseMessage, GlobalConstants.error);
      }
    });
  }
}



