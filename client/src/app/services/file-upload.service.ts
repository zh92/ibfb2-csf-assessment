import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { UploadResult } from '../models/uploadresult';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor(private httpClient: HttpClient) { }

  upload(form: any, image: any){
    const formData = new FormData()
    formData.set("name", form["name"])
    formData.set("title", form["title"])
    formData.set("comments", form["comments"])
    formData.set("archive", form["archive"])
    return lastValueFrom(this.httpClient.post("/upload", formData))
  }
}
