import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from '../../node_modules/ngx-toastr';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ResponseHandlerInterceptorService implements HttpInterceptor {

  constructor(public toastr: ToastrService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      tap((success) => {
        if (success instanceof HttpResponse) {
          if(success.url.endsWith('login') ||
          success.url.endsWith('register') ||
          success.url.includes('create') ||
          success.url.includes('delete') 

              ){
            this.toastr.success('success', 'success');
          }

        }



      }),
      catchError((err) => {
        console.log(err);
        this.toastr.error(err.error.message, 'Error');
        throw err;
      })
    )
  }

}
