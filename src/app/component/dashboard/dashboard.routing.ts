import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { CycleComponent } from '../cycle/cycle.component';
import { RouteGuardService } from 'src/app/service/route-guard.service';

export const DashboardRoutes: Routes = [{
  path: '',
  component: DashboardComponent
},
  {
    path: 'cycle-by-sous-systeme/:id',
    component: CycleComponent,
    canActivate:[RouteGuardService],
    data:{
      expectedRole:['apprenant', 'enseignant']
    }
  }
];
