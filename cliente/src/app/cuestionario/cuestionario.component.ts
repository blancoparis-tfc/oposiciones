import { Component, OnInit } from '@angular/core'
import {MD_LIST_DIRECTIVES} from '@angular2-material/list'
import { ROUTER_DIRECTIVES } from '@angular/router';
import { OposicionesService} from '../oposiciones.service'
import {OposicionPlana} from '../temario/temario'



@Component({
  moduleId: module.id,
  selector: 'app-cuestionario',
  templateUrl: 'cuestionario.component.html',
  styleUrls: ['cuestionario.component.css'],
  providers:[OposicionesService],
  directives:[MD_LIST_DIRECTIVES,ROUTER_DIRECTIVES]
})
export class CuestionarioComponent implements OnInit {

  private oposicionPlana:OposicionPlana

  constructor(private oposicionesService:OposicionesService) {
    this.oposicionPlana=new OposicionPlana(null,null,null,null);
   }

  ngOnInit() {
    this.oposicionesService.getOposicionPlana(1)
        .subscribe(data=>{
          console.info(data)
          this.oposicionPlana=data});
  }

}
