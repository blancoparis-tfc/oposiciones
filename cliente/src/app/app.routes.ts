import {provideRouter,RouterConfig} from "@angular/router"
import {PreguntasOposicionComponent} from './preguntas-oposicion/preguntas-oposicion.component'
import {CuestionarioComponent} from './cuestionario/cuestionario.component'
import {TemarioComponent} from './temario/temario.component'
import {TemaComponent} from './tema/tema.component'
import {DocumentoComponent} from './documento/documento.component'
const rutas : RouterConfig =[
    {path:'',component:CuestionarioComponent},
    {path:'preguntas/:id',component:PreguntasOposicionComponent},
    {path:'temario/:id',component:TemarioComponent},
    {path:'tema/:bloque/:tema',component:TemaComponent},
    {path:'documento',component:DocumentoComponent}
];

export const appRouterProviders = [
    provideRouter(rutas)

];