import { Component } from '@angular/core';
import { ContentService } from './content.service';
import { IPost } from './shared/interfaces';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  posts: IPost[] | undefined;
  n: number =5;

  constructor(private contentService: ContentService) {
    this.fetchRecentPosts(this.n);
  }


  fetchRecentPosts(count: number): void {
    this.posts=undefined;
    this.contentService.loadPosts(7).subscribe(posts => this.posts = posts.slice(0,count));
  }
}
