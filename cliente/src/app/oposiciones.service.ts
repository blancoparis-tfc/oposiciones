import { Injectable } from '@angular/core'
import {Jsonp,Response,Request,URLSearchParams,Http } from '@angular/http'
import {Observable} from 'rxjs/Observable'
import {Pregunta} from './pregunta/pregunta'
import {Bloque,OposicionPlana} from './temario/temario'

@Injectable()
export class OposicionesService {
  private  testUrl='http://192.168.1.45:8080/test';

  private cuestionarioUrlMock='temario2016.json'
  private cuestionarioUrl='http://192.168.1.45:8080/temario'
  private oposicionPlanaUrl='http://192.168.1.45:8080/oposicion'
  constructor(private http: Http) { }

  public getOposicionPlana(id:number):Observable<OposicionPlana>{
    return this.http.get(this.oposicionPlanaUrl+"/"+id)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  public getTema(bloqueId:string,numeroTema:string){
    return this.http.get(this.cuestionarioUrlMock)
                    .map(this.extractData)
                    .map(bloques=>bloques.find(bloque=>bloque.id==bloqueId))
                    .map(bloque=>bloque.temas.find(tema=>tema.numero==numeroTema))
                    .catch(this.handleError);
  }

  public getTemario(anio:string):Observable<Bloque[]>{
    let params = new URLSearchParams();
    params.set('anio',anio)
    return this.http.get(this.cuestionarioUrl,{search:params})
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  public getTest(anio:string):Observable<Pregunta[]>{
  let params = new URLSearchParams();
  params.set('anio',anio)
  return this.http.get(this.testUrl,{search:params})
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
