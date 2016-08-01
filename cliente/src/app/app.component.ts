import { Component } from '@angular/core';
import {PreguntasOposicionComponent} from './preguntas-oposicion/preguntas-oposicion.component'
@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  directives:[PreguntasOposicionComponent]
})
export class AppComponent {
  title = 'app works!';
}
