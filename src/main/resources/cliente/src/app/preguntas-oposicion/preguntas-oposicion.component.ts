import { Component, OnInit,Input } from '@angular/core'
import {MD_TOOLBAR_DIRECTIVES} from '@angular2-material/toolbar'
import {PreguntaComponent} from '../pregunta/pregunta.component'
import {Pregunta,Opcion} from '../pregunta/pregunta'
@Component({
  moduleId: module.id,
  selector: 'app-preguntas-oposicion',
  templateUrl: 'preguntas-oposicion.component.html',
  styleUrls: ['preguntas-oposicion.component.css'],
    directives:[MD_TOOLBAR_DIRECTIVES,PreguntaComponent]
})
export class PreguntasOposicionComponent implements OnInit {

    preguntas:Array<Pregunta>

  constructor() { }

  ngOnInit() {
    this.preguntas=this.preguntasMock()
  }
  private preguntasMock(){
    let preguntas= new Array<Pregunta>()
    preguntas.push(new Pregunta("Bloque I",1,"Esta es la pregunta 1",false,this.opcionesMock()))
    preguntas.push(new Pregunta("Bloque I",2,"Esta es la pregunta 2",false,this.opcionesMock()))
    return preguntas  
}

  private opcionesMock(){
    let opciones = new Array<Opcion>();
    opciones.push(new Opcion("a","Es la solución (a)",false));
    opciones.push(new Opcion("b","Es la solución (b)",false));
    opciones.push(new Opcion("c","Es la solución (c)",true));
    opciones.push(new Opcion("d","Es la solución (d)",false));
    return opciones;
  }
}
