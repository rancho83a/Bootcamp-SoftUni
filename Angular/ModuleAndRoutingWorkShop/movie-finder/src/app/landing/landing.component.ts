import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  @ViewChild('f') searchForm: NgForm | undefined;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  search() {
    const querySearch = this.searchForm?.value[' query '];
    console.log("from landing:" + querySearch)

    this.router.navigate([ '/movies/search' ], { queryParams: { search: querySearch } });

  }

}
