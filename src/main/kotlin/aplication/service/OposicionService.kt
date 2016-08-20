package aplication.service

import aplication.dao.Oposicion
import aplication.dao.OposicionDao
import aplication.dao.OposicionPlana
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Created by davidTorre on 05/08/2016.
 */




val logger = LoggerFactory.getLogger("aplication.service.OposicionService")
const val oposicionMock=1L
val anioTest = arrayOf("2014","2013","2011")


interface OposicionesService {
    fun obtenerOposicionDefecto(): Oposicion
    fun initOposicion()
    fun obtenerDetallOposicion(id: Long): DetallePosicion
}
data class DetallePosicion(val idOpocion:String, val descripnion:String
                          ,val plan:List<String>,val test:List<String>)

@Service
@Transactional
open class DefaultOposicionService(val oposicionDao:OposicionDao,val temarioService:TemarioService):OposicionesService{

   @Autowired var testService:TestService?=null

   override fun obtenerOposicionDefecto()= oposicionDao.findOne(oposicionMock);

   override fun initOposicion(){
       if(obtenerOposicionDefecto()==null){
           logger.info("tenemos que crear la oposicion")
           oposicionDao.save(Oposicion(null,"Informatica (Ministerio)","A2"))
       }
       temarioService.crearTemario(obtenerOposicionDefecto(),"2016")
       //println("Es numero de elementos ${anioTest.size}")
       anioTest.forEach {
           //println("   Anio;${it}")
           testService!!.crearTest(oposicionMock,it)
       }
   }

    override fun obtenerDetallOposicion(id:Long ):DetallePosicion{
        val oposicionesPlanas=oposicionDao.findByOposicion()
        val oposcionPlana = oposicionesPlanas.first()
        return DetallePosicion(oposcionPlana.idOposicion.toString()
                ,oposcionPlana.descripcion
                ,oposicionesPlanas.map{it.anioTemario}.distinct().toList()
                ,oposicionesPlanas.map{it.anioTest}.distinct().toList());

    }
}
