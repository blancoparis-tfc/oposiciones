import {provideRouter,RouterConfig} from "@angular/router"
import {PreguntasOposicionComponent} from './preguntas-oposicion/preguntas-oposicion.component'
import {CuestionarioComponent} from './cuestionario/cuestionario.component'
import {TemarioComponent} from './temario/temario.component'
import {TemaComponent} from './tema/tema.component'
const rutas : RouterConfig =[
    {path:'',component:CuestionarioComponent},
    {path:'preguntas/:id',component:PreguntasOposicionComponent},
    {path:'temario',component:TemarioComponent},
    {path:'tema/:bloque/:tema',component:TemaComponent}
];

export const appRouterProviders = [
    provideRouter(rutas)

];