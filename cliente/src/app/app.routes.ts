import { Routes, RouterModule } from '@angular/router';
import {PreguntasOposicionComponent} from './preguntas-oposicion/preguntas-oposicion.component'
import {CuestionarioComponent} from './cuestionario/cuestionario.component'
import {TemarioComponent} from './temario/temario.component'
import {TemaComponent} from './tema/tema.component'
import {DocumentoComponent} from './documento/documento.component'
const rutas : Routes =[
    {path:'preguntas/:id',component:PreguntasOposicionComponent},
    {path:'temario/:id',component:TemarioComponent},
    {path:'tema/:bloque/:tema',component:TemaComponent},
    {path:'documento',component:DocumentoComponent},
    {path:'',component:CuestionarioComponent}
];
export const appRoutingProviders: any[] = [

];
export const routing = RouterModule.forRoot(rutas);