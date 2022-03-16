import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PostsService } from '../posts.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private postService:PostsService) { }

  ngOnInit(): void {
  }
  submitHandler(form: NgForm){
    const [title, content] = form.value;
    this.postService.create(title,content);
  }

}
