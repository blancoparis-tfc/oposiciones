package aplication

import aplication.componentes.pdf.Bloque
import aplication.componentes.pdf.leerTemario
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by davidTorre on 02/08/2016.
 */
@RestController
class TemarioController{
    @RequestMapping(value="/temario",method= arrayOf(RequestMethod.GET))
    fun getTemario(@RequestParam(value="anio") anio:String):List<Bloque>{
        return leerTemario(anio);
    }
}