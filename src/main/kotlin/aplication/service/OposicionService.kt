package aplication.service

import aplication.dao.Oposicion
import aplication.dao.OposicionDao
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by davidTorre on 05/08/2016.
 */

const val oposicionMock=1L

val anioTest = arrayOf("2014","2013","2011")

interface OposicionesService{
    fun obtenerOposicionDefecto():Oposicion
    fun initOposicion()
}
val logger = LoggerFactory.getLogger("aplication.service.OposicionService")
@Service
@Transactional
open class DefaultOposicionService(val oposicionDao:OposicionDao):OposicionesService{

    @Autowired var testService:TestService?=null

   override fun obtenerOposicionDefecto()= oposicionDao.findOne(oposicionMock);

   override fun initOposicion(){
       if(obtenerOposicionDefecto()==null){
           logger.info("tenemos que crear la oposicion")
           oposicionDao.save(Oposicion(null,"Informatica (Ministerio)","A2"))
       }
       //println("Es numero de elementos ${anioTest.size}")
       anioTest.forEach {
           //println("   Anio;${it}")
           testService!!.crearTest(oposicionMock,it)
       }
   }
}
