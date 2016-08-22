import { Component, OnInit,Input } from '@angular/core'
import { Router, ActivatedRoute }       from '@angular/router';
import { PreguntaComponent} from '../pregunta/pregunta.component'
import { Pregunta,Opcion} from '../pregunta/pregunta'
import { OposicionesService} from '../oposiciones.service'
//import {MD_ICON_DIRECTIVES,MdIconRegistry,MdIcon} from '@angular2-material/icon'
import {MD_PROGRESS_BAR_DIRECTIVES} from '@angular2-material/progress-bar'
@Component({
  //moduleId: module.id,
  selector: 'app-preguntas-oposicion',
  templateUrl: 'preguntas-oposicion.component.html',
  styleUrls: ['preguntas-oposicion.component.css'],
  providers:[OposicionesService,MD_PROGRESS_BAR_DIRECTIVES],
    directives:[PreguntaComponent]
})
export class PreguntasOposicionComponent implements OnInit {

  preguntas:Array<Pregunta>

  private sub:any;
  private anio:string;
  private avance:number;

  constructor(
    private route: ActivatedRoute,
    private oposicionesService:OposicionesService
    ) {

     //mdIconRegistry.registerFontClassAlias('myfont', 'my-icon-font-class'); 
     }

  ngOnInit() {
    this.avance=20;
    this.sub = this.route.params.subscribe(params => {
        this.anio = params['id'];
        this.oposicionesService.getTest(this.anio).subscribe(data=>{
          this.preguntas=data
          this.avance=100
        });

    });
  }
}
