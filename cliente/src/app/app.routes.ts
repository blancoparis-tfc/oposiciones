import {provideRouter,RouterConfig} from "@angular/router"
import {PreguntasOposicionComponent} from './preguntas-oposicion/preguntas-oposicion.component'
import {CuestionarioComponent} from './cuestionario/cuestionario.component'
const rutas : RouterConfig =[
    {path:'',component:CuestionarioComponent},
    {path:'preguntas/:id',component:PreguntasOposicionComponent}
];

export const appRouterProviders = [
    provideRouter(rutas)
];