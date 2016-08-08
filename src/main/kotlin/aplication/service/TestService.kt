package aplication.service

import aplication.componentes.pdf.Opciones
import aplication.componentes.pdf.PreguntaDto
import aplication.componentes.pdf.obtenerTestAnio
import aplication.dao.*
import org.hibernate.collection.internal.PersistentSet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.comparisons.compareBy

/**
 * Created by davidTorre on 06/08/2016.
 */

@Service
@Transactional
open class TestService(val preguntaDao: PreguntaDao){

    @Autowired var oposicionesService: OposicionesService? = null; // Es po

    open fun crearTest( oposicion:Long, anio:String):List<PreguntaDto>{
        val oposicionDefecto= this.oposicionesService!!.obtenerOposicionDefecto()
        var test =oposicionDefecto.test!!.find { it.periodo.equals(anio) }
        var valdev:ArrayList<PreguntaDto>
        if(test==null) {
            valdev = obtenerTestAnio(anio) as ArrayList<PreguntaDto>
            val preguntas = parser(valdev)
            test = Test(null, anio, preguntas)
            var tests = oposicionDefecto.test as java.util.Set<Test>
            tests!!.add(test)
            oposicionDefecto.test = tests as Set<Test>
        }else{
            valdev =parserPlano(preguntaDao.findByTest(test))
        }
        return valdev
   }
    private fun parserPlano(preguntas:List<PreguntaPlano>)
            :ArrayList<PreguntaDto>
    {
        return preguntas.sortedWith(compareBy({it.id})).groupBy { it.id }
                .map {
                    PreguntaDto(it.value.get(0).id,"Pendiente",it.value.get(0).numero,it.value.get(0).enunciado,it.value.get(0).anulado,
                    it.value.map{Opciones(it.idApartado,it.apartado,it.solucion,it.correcto) }as ArrayList<Opciones>)
                } as ArrayList<PreguntaDto>
    }

   private fun parser(preguntas:List<PreguntaDto>):List<Pregunta>{
        return preguntas.map {Pregunta(null,it.numero.toLong(),it.enunciado,it.anulada
                , it.opciones!!.map { Apartado(null,it.apartado,it.solucion,it.correcta)})}
   }
}
