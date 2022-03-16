import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'postCount',
  pure: true//default
})
export class PostCountPipe implements PipeTransform {

  transform(value: Array<any>, userId: number, other: any): unknown {
    console.log(other);
    return value.reduce((acc,curr)=> {
      if(curr.userId===userId){
        return acc+1;
      }
      return acc;
    },0)

  }

}
