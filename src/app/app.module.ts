import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { SignupComponent } from './component/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatAutocompleteModule } from '@angular/material/autocomplete';
 import { MatButtonModule } from '@angular/material/button';
 import { MatButtonToggleModule } from '@angular/material/button-toggle';
 import { MatCardModule } from '@angular/material/card';
 import { MatCheckboxModule } from '@angular/material/checkbox';
 import { MatChipsModule } from '@angular/material/chips';
 import { MatDatepickerModule } from '@angular/material/datepicker';
 import { MatDialogModule } from '@angular/material/dialog';
 import { MatExpansionModule } from '@angular/material/expansion';
 import { MatFormFieldModule } from '@angular/material/form-field';
 import { MatGridListModule } from '@angular/material/grid-list';
 import { MatIconModule } from '@angular/material/icon';
 import { MatInputModule } from '@angular/material/input';
 import { MatListModule } from '@angular/material/list';
 import { MatMenuModule } from '@angular/material/menu';
 import { MatPaginatorModule } from '@angular/material/paginator';
 import { MatProgressBarModule } from '@angular/material/progress-bar';
 import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
 import { MatRadioModule } from '@angular/material/radio';
 import { MatSelectModule } from '@angular/material/select';
 import { MatSidenavModule } from '@angular/material/sidenav';
 import { MatSliderModule } from '@angular/material/slider';
 import { MatSlideToggleModule } from '@angular/material/slide-toggle';
 import { MatSnackBarModule } from '@angular/material/snack-bar';
 import { MatSortModule } from '@angular/material/sort';
 import { MatTableModule } from '@angular/material/table';
 import { MatTabsModule } from '@angular/material/tabs';
 import { MatToolbarModule } from '@angular/material/toolbar';
 import { MatTooltipModule } from '@angular/material/tooltip';
 import { MatStepperModule } from '@angular/material/stepper';
 import { MatBadgeModule } from '@angular/material/badge';
 import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
 import { MatBottomSheetModule } from '@angular/material/bottom-sheet';

 import { CdkTableModule } from '@angular/cdk/table';
 import { CdkAccordionModule } from '@angular/cdk/accordion';
 import { A11yModule } from '@angular/cdk/a11y';
 import { BidiModule } from '@angular/cdk/bidi';
 import { OverlayModule } from '@angular/cdk/overlay';
 import { PlatformModule } from '@angular/cdk/platform';
 import { ObserversModule } from '@angular/cdk/observers';
 import { PortalModule } from '@angular/cdk/portal';
 import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxUiLoaderConfig, NgxUiLoaderModule, SPINNER } from 'ngx-ui-loader';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './component/login/login.component';
import { UserPageComponent } from './component/user-page/user-page.component';
// import { DashboardComponent } from './component/dashboard/dashboard.component';
import { TokenInterceptorInterceptor } from './service/token-interceptor.interceptor';
import { NiveauComponent } from './component/niveau/niveau.component';
import { MatiereComponent } from './component/matiere/matiere.component';
import { ChapitreComponent } from './component/chapitre/chapitre.component';
import { LeconComponent } from './component/lecon/lecon.component';
import { CycleComponent } from './component/cycle/cycle.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FullComponent } from './component/full/full.component';
import { AddLeconComponent } from './component/add-lecon/add-lecon.component';
import { AddChapterComponent } from './component/add-chapter/add-chapter.component';
import { UploadDocumentComponent } from './component/upload-document/upload-document.component';
import { UploadFileComponent } from './component/upload-file/upload-file.component';


const ngxUiLoaderConfig: NgxUiLoaderConfig = {
  text:"Loading...",
  textColor:"#FFFFFF",
  textPosition:"center-center",
  bgsColor:"#7b1fa2",
  fgsColor:"#7b1fa2",
  fgsType:SPINNER.squareJellyBox,
  fgsSize:100,
  hasProgressBar: false
}
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SignupComponent,
    LoginComponent,
    UserPageComponent,
    NiveauComponent,
    MatiereComponent,
    ChapitreComponent,
    LeconComponent,
    CycleComponent,
    FullComponent,
    AddLeconComponent,
    AddChapterComponent,
    UploadDocumentComponent,
    UploadFileComponent
  ],
  imports: [
    MatAutocompleteModule,
    MatButtonModule,
    MatBottomSheetModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatTableModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatBadgeModule,
    MatSidenavModule,
    ReactiveFormsModule,
    MatSlideToggleModule,
    MatSliderModule,
    MatSnackBarModule,
    MatSortModule,
    MatStepperModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatNativeDateModule,
    CdkTableModule,
    A11yModule,
    BidiModule,
    CdkAccordionModule,
    ObserversModule,
    OverlayModule,
    PlatformModule,
    PortalModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSidenavModule,
    FlexLayoutModule,
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig)
  ],
  providers: [HttpClientModule, {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorInterceptor, multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
