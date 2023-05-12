import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { FileDetails } from '../models/filedetails';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private GET_URL = "/bundleId"
  private headers = new HttpHeaders().set("Content-Type", "application/json")
  constructor(private httpClient: HttpClient) { }

  upload(form: any){
    const formData = new FormData()
    formData.set("name", form["name"])
    formData.set("title", form["title"])
    formData.set("comments", form["comments"])
    formData.set("archive", form["archive"])
    return lastValueFrom(this.httpClient.post("/upload", formData))
  }

  getDetails(bundleId: string): Promise<any>{
    return lastValueFrom(this.httpClient.get<FileDetails>(
      this.GET_URL + "/" + bundleId, {headers: this.headers}))
  }
}
