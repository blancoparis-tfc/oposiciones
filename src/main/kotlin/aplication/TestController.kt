package aplication

import aplication.componentes.pdf.PreguntaDto
import aplication.service.TestService
import aplication.service.oposicionMock
import org.springframework.web.bind.annotation.*

/**
 * Created by davidTorre on 23/07/2016.
 */
@RestController
@CrossOrigin
class TestController(val testService: TestService){
    @RequestMapping(value="/test",method= arrayOf(RequestMethod.GET))
    fun getTest(@RequestParam(value="anio") anio:String):List<PreguntaDto>{
        return this.testService.crearTest(oposicionMock,anio);
    }


}