import { Component } from '@angular/core';
import {MD_TOOLBAR_DIRECTIVES} from '@angular2-material/toolbar'
import {ROUTER_DIRECTIVES } from '@angular/router'

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  directives:[ROUTER_DIRECTIVES, MD_TOOLBAR_DIRECTIVES]
})
export class AppComponent {
  title = 'AO';
}
