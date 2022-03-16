import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

const apiURL = environment.apiURL;


export class AppHttpInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


        let newHeaders = {
            'Content-Type': 'application/json',
            Autorization: ''
        };

        const token = localStorage.getItem('token');


        if (req.url.includes("feed")) {
            newHeaders = {
                'Content-Type': 'application/json',
                Autorization: `Bearer ${token}`
            };
        }


        return next.handle(req.clone({
            url: `${apiURL}/${req.url}`,
            setHeaders: newHeaders
        }))
            .pipe(tap(console.log));
    }


}