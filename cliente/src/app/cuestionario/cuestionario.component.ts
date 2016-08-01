import { Component, OnInit } from '@angular/core'
import {MD_LIST_DIRECTIVES} from '@angular2-material/list'
import { ROUTER_DIRECTIVES } from '@angular/router';


@Component({
  moduleId: module.id,
  selector: 'app-cuestionario',
  templateUrl: 'cuestionario.component.html',
  styleUrls: ['cuestionario.component.css'],
  directives:[MD_LIST_DIRECTIVES,ROUTER_DIRECTIVES]
})
export class CuestionarioComponent implements OnInit {

  private anios:Array<String>;

  constructor() {
    this.anios=new Array<String>();
    this.anios.push("2014");
    this.anios.push("2013");
    this.anios.push("2011");
   }

  ngOnInit() {
  }

}
