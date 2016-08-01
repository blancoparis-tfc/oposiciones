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

  constructor() { }

  ngOnInit() {
  }

}
