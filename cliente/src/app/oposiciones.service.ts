import { Injectable } from '@angular/core'
import {Jsonp,Response,Request,URLSearchParams,Http } from '@angular/http'
import {Observable} from 'rxjs/Observable'
import {Pregunta} from './pregunta/pregunta'

@Injectable()
export class OposicionesService {
  private  testUrl='http://192.168.1.45:8080/test?anio=2014';

  private testUrlMock='preguntas2014.json';

  constructor(private http: Http) { }

  public getTest(anio:string):Observable<Pregunta[]>{
    /*let parametros = new URLSearchParams();
    parametros.set('format')*/
  return this.http.get('preguntas'+anio+'.json')
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  private extractData(res: Response) {
    console.info(res);
    let body = res.json();
    return body || { };
  }
  
  private handleError (error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }

}
