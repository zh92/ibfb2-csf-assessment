import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StorageComponent } from './components/storage.component';
import { UploadComponent } from './components/upload.component';

const routes: Routes = [
  {path:"", component: StorageComponent},
  {path:"/upload", component: UploadComponent},
  {path:"bundle/:bundleId", component: UploadComponent},
  {path:"**", redirectTo:"/", pathMatch: "full"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
