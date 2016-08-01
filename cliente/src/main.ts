import 'rxjs/add/operator/map';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/observable/interval';
import 'rxjs/add/observable/forkJoin';
import 'rxjs/add/operator/publishReplay';
import 'rxjs/add/operator/catch'


import { bootstrap } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { AppComponent, environment } from './app/';
import {disableDeprecatedForms, provideForms} from '@angular/forms'; 
import {HTTP_PROVIDERS,JSONP_PROVIDERS} from '@angular/http';

if (environment.production) {
  enableProdMode();
}

bootstrap(AppComponent,[disableDeprecatedForms(),provideForms(),HTTP_PROVIDERS,JSONP_PROVIDERS]);
