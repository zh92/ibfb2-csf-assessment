import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FileUploadService } from '../services/file-upload.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  form!: FormGroup
  result!: string

  constructor(private fb: FormBuilder, private router: Router,
    private uploadSvc: FileUploadService) {}

  ngOnInit(): void {
    this.form = this.createForm()
  }

  upload(){
    const formVal = this.form.value;
    const result = this.uploadSvc.upload(formVal);
    this.router.navigate(['/bundle/:bundleId', result])
  }

  private createForm(): FormGroup{
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      title: this.fb.control<string>('', [Validators.required]),
      comments: this.fb.control<string>(''),
      archive: this.fb.control<string>('', [Validators.required])
    })
  }
}
