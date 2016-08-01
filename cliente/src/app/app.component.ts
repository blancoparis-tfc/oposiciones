import { Component,ViewChild } from '@angular/core'
import {ROUTER_DIRECTIVES } from '@angular/router'
import {MD_TOOLBAR_DIRECTIVES} from '@angular2-material/toolbar'
@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  directives:[MD_TOOLBAR_DIRECTIVES,ROUTER_DIRECTIVES]
})
export class AppComponent {
  title = 'AO';

}
