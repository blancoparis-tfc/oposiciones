import { Component, OnInit } from '@angular/core';
import {MD_LIST_DIRECTIVES} from '@angular2-material/list'
import {Bloque} from './temario'
import {OposicionesService} from '../oposiciones.service'
import { ROUTER_DIRECTIVES } from '@angular/router';


@Component({
  moduleId: module.id,
  selector: 'app-temario',
  templateUrl: 'temario.component.html',
  styleUrls: ['temario.component.css'],
  providers:[OposicionesService],
  directives:[MD_LIST_DIRECTIVES,ROUTER_DIRECTIVES]
})
export class TemarioComponent implements OnInit {

  public temario:Array<Bloque>
  private avance:number


  constructor(private oposicionesService:OposicionesService ) { }

  ngOnInit() {
    this.avance=20;
        this.oposicionesService.getTemario("2016").subscribe(data=>{
          this.temario=data
          this.temario.length
          this.avance=100
        });

  }

}
