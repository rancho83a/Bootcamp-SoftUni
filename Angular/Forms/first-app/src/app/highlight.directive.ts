import { Directive, ElementRef, HostBinding, Input, OnChanges, SimpleChanges } from '@angular/core';

@Directive({
  selector: '[appHighlight]',
  exportAs: "appHighlight"
})
export class HighlightDirective //implements OnChanges {
  {

  @Input() set appHighlight(isActive:boolean) {
    if(isActive){
      this.color='red'
    } else {
      this.color='blue'
    }
  }
    @HostBinding('style.color') color = 'blue'
  
  
  
  
  // @Input() isActive!: boolean

  // constructor(private elementRef: ElementRef) { }
  // ngOnChanges(
  // ){
  //   //changes: SimpleChanges): void
    
  //   //if (changes['isActive'].currentValue{
     
  //   if (this.isActive) {
  //     this.elementRef.nativeElement.style.color = 'red';
  //   } else {
  //     this.elementRef.nativeElement.style.color = 'blue';

  //   }
  // }

}
