import { Component, OnInit, Input } from '@angular/core';
import {MdUniqueSelectionDispatcher} from '@angular2-material/core';
//import {MD_CARD_DIRECTIVES} from '@angular2-material/card';
//import {MD_BUTTON_DIRECTIVES} from '@angular2-material/button';
//import {MD_RADIO_DIRECTIVES} from '@angular2-material/radio';
import {Opcion,Pregunta} from './pregunta';


@Component({
//  moduleId: module.id,
  selector: 'app-pregunta',
  templateUrl: 'pregunta.component.html',
  styleUrls: ['pregunta.component.css'],
    providers:[MdUniqueSelectionDispatcher],
  directives:[PreguntaComponent]
})
export class PreguntaComponent implements OnInit {
  
  @Input('pregunta') public pregunta:Pregunta
  public solucion:string;
  constructor() { 
  }

  ngOnInit() {
   // this.pregunta=new Pregunta("Bloque I",1,"Esta es la pregunta ",false,this.opcionesMock())
    this.solucion="";
  }

  public corregir(){
    let opcionCorrecta=this.pregunta.opciones.find(opcion=>opcion.correcta);
    opcionCorrecta.estado=opcionCorrecta.apartado==this.solucion?'B':'M';    
  }

}
