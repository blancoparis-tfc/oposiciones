package aplication

import aplication.componentes.pdf.BloqueDto
import aplication.componentes.pdf.PreguntaDto
import aplication.service.*
import org.springframework.web.bind.annotation.*

/**
 * Created by davidTorre on 11/08/2016.
 */
/**
 * Created by davidTorre on 02/08/2016.
 */

val parser =org.commonmark.parser.Parser.builder().build();
val renderer=org.commonmark.html.HtmlRenderer.builder().build();

@RestController
@CrossOrigin
class TemarioController(val temarioService: TemarioService, val oposicionesService: OposicionesService){
    @RequestMapping(value="/temario",method= arrayOf(RequestMethod.GET))
    fun getTemario(@RequestParam(value="anio") anio:String):List<BloqueDto>{
        //return leerTemario(anio);
        return temarioService.obtenerTemario(oposicionesService.obtenerOposicionDefecto().plan!!.find{ it.anio.equals(anio) })
    }
    // Ejemplo para procesar markDown
    @RequestMapping(value="/tema/{id}",produces = arrayOf("text/html"))
    fun getTema(@PathVariable id:Long):String{
        return renderer.render(parser.parse(" # david \n esto es *Sparta*"))
    }
}

@RestController
@CrossOrigin
class TestController(val testService: TestService){
    @RequestMapping(value="/test",method= arrayOf(RequestMethod.GET))
    fun getTest(@RequestParam(value="anio") anio:String):List<PreguntaDto>{
        return this.testService.crearTest(oposicionMock,anio);
    }
}

@RestController
@CrossOrigin
class OposicionController(var oposicionesService: OposicionesService){
    @RequestMapping(value="/oposicion/{id}")
    fun getOposicion(@PathVariable id:Long): DetallePosicion {
        return oposicionesService.obtenerDetallOposicion(-1);
    }
}