import { Component, OnInit,Input } from '@angular/core'
import { Router, ActivatedRoute }       from '@angular/router';
import { PreguntaComponent} from '../pregunta/pregunta.component'
import { Pregunta,Opcion} from '../pregunta/pregunta'
import { OposicionesService} from '../oposiciones.service'
@Component({
  moduleId: module.id,
  selector: 'app-preguntas-oposicion',
  templateUrl: 'preguntas-oposicion.component.html',
  styleUrls: ['preguntas-oposicion.component.css'],
  providers:[OposicionesService],
    directives:[PreguntaComponent]
})
export class PreguntasOposicionComponent implements OnInit {

  preguntas:Array<Pregunta>

  private sub:any;
  private anio:string;

  constructor(
    private route: ActivatedRoute,
    private oposicionesService:OposicionesService
    ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
        this.anio = params['id'];
        this.oposicionesService.getTest(this.anio).subscribe(data=>this.preguntas=data);
    });
  }
}
