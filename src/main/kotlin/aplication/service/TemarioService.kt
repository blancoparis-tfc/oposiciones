package aplication.service

import aplication.componentes.pdf.BloqueDto
import aplication.componentes.pdf.Temario
import aplication.componentes.pdf.leerTemario
import aplication.dao.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.comparisons.compareBy

/**
 * Created by davidTorre on 10/08/2016.
 */


@Service
@Transactional
open class TemarioService(val oposicionDao: OposicionDao,val bloqueDao:BloqueDao){
    open fun crearTemario(oposicion:Oposicion,anio:String){
        (oposicion.plan as java.util.Set<Plan>).add(Plan(null,anio,parser(leerTemario(anio))))
        oposicionDao.save(oposicion)
    }

    open fun obtenerTemario(plan:Plan?):List<BloqueDto>{
        return parserPlano(bloqueDao.findByPlan(plan!!))
    }

    private fun parser (bloques:List<BloqueDto>):List<Bloque>{
        return bloques.map { Bloque(null,it.bloque
                ,it.temas.map { Tema(null,it.numero,it.tema,
                it.apartados) })
        }
    }

    private fun parserPlano(bloques:List<BloquePlano>):List<BloqueDto>{
        return bloques.sortedWith(compareBy({it.idBloque})).groupBy { it.idBloque }
            .map {
                BloqueDto(it.value.get(0).idBloque,it.value.get(0).descripcionBloque
                        ,it.value.map{ Temario(it.numeroTema,it.tema,null)})
            }
    }
}
