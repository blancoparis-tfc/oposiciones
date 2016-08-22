import { Component, OnInit } from '@angular/core'
import { Router, ActivatedRoute } from '@angular/router'
import { OposicionesService} from '../oposiciones.service'
import {MD_CARD_DIRECTIVES} from '@angular2-material/card'
import {MD_BUTTON_DIRECTIVES} from '@angular2-material/button';
import { ROUTER_DIRECTIVES } from '@angular/router';

import {Temario} from '../temario/temario'

@Component({
//  moduleId: module.id,
  selector: 'app-tema',
  templateUrl: 'tema.component.html',
  styleUrls: ['tema.component.css'],
  providers:[OposicionesService],
  directives:[ROUTER_DIRECTIVES,MD_CARD_DIRECTIVES,MD_BUTTON_DIRECTIVES]
})
export class TemaComponent implements OnInit {

  private sub:any
  private bloque:string
  private tema:string
  private temario:Temario

  constructor(private route: ActivatedRoute,private oposicionesService:OposicionesService, private router:Router) {
    this.temario=new Temario("","",new Array<string>());
  }

  ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
          this.bloque=params['bloque'];
          this.tema=params['tema'];
          this.oposicionesService.getTema(this.bloque,this.tema).subscribe(data=>{
            console.info(data)
            this.temario=data
          })
        });
  }

  nuevoDocumento(){
    console.info('crear nuevo documento');
    this.router.navigate(['/documento']);
  }

}
