package aplication

import aplication.componentes.pdf.Pregunta
import aplication.componentes.pdf.obtenerTestAnio
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by davidTorre on 23/07/2016.
 */
@RestController
class TestController{
    @RequestMapping(value="/test",method= arrayOf(RequestMethod.GET))
    fun getTest(@RequestParam(value="anio") anio:String):List<Pregunta>{
        return obtenerTestAnio(anio);
    }
}