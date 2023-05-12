import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { FileDetails } from '../models/filedetails';
import { FileUploadService } from '../services/file-upload.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit, OnDestroy {

  bundleId = ""
  param$!: Subscription
  fileDetails!: FileDetails

  constructor(private router: Router, private activatedRoute: ActivatedRoute,
    private uploadSvc: FileUploadService) {}

  ngOnInit(): void {
      this.param$ = this.activatedRoute.params.subscribe(
        async (params) => {
          this.bundleId = params["bundleId"]
          const l = await this.uploadSvc.getDetails(this.bundleId)
          this.fileDetails = l
        }
      )
  }
  ngOnDestroy(): void {
      this.param$.unsubscribe()
  }
}
