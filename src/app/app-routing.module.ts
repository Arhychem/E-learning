import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { UserPageComponent } from './component/user-page/user-page.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { NiveauComponent } from './component/niveau/niveau.component';
import { MatiereComponent } from './component/matiere/matiere.component';
import { ChapitreComponent } from './component/chapitre/chapitre.component';
import { LeconComponent } from './component/lecon/lecon.component';
import { CycleComponent } from './component/cycle/cycle.component';
import { FullComponent } from './component/full/full.component';
import { RouteGuardService } from './service/route-guard.service';


const routes: Routes = [
  {path: '', component: HomeComponent },
  {
    path: 'home',
    component: FullComponent,
    children: [
      {
        path: '',
        redirectTo: '/home/dashboard',
        pathMatch: 'full',
      },
      {
        path: '',
        loadChildren:
          () => import('./material-component/material.module').then(m => m.MaterialComponentsModule),
          canActivate:[RouteGuardService],
          data:{
            expectedRole:['apprenant', 'enseignant']
          }
      },
      {
        path: 'dashboard',
        loadChildren: () => import('src/app/component/dashboard/dashboard.module').then(m => m.DashboardModule),
        canActivate:[RouteGuardService],
        data:{
          expectedRole:['apprenant', 'enseignant']
        }
      }
    ]
  },
  {path:'dashboard', component: DashboardComponent},
  {path:'niveau-by-cycle/:id', component: NiveauComponent},
  {path:'matiere-by-niveau/:id', component: MatiereComponent},
  {path:'chapitre-by-matiere/:id', component: ChapitreComponent},
  {path:'cycle-by-sous-systeme/:id', component: CycleComponent},
  {path:'lecon-by-chapitre/:id', component: LeconComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
