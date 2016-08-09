package aplication

import aplication.componentes.pdf.Bloque
import aplication.componentes.pdf.leerTemario
import org.springframework.web.bind.annotation.*

/**
 * Created by davidTorre on 02/08/2016.
 */

val parser =org.commonmark.parser.Parser.builder().build();
val renderer=org.commonmark.html.HtmlRenderer.builder().build();


@RestController
class TemarioController{
    @RequestMapping(value="/temario",method= arrayOf(RequestMethod.GET))
    fun getTemario(@RequestParam(value="anio") anio:String):List<Bloque>{
        return leerTemario(anio);
    }
    // Ejemplo para procesar markDown
    @RequestMapping(value="/tema/{id}",produces = arrayOf("text/html"))
    fun getTema(@PathVariable id:Long):String{
        return renderer.render(parser.parse(" # david \n esto es *Sparta*"))
    }
}