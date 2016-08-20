import aplication.componentes.pdf.*

import java.util.*

/**
 * Created by davidTorre on 20/07/2016.
 */

/*
fun main(args: Array<String>) {
    var pdfTest=Thread.currentThread().contextClassLoader.getResource("2013Test.pdf").path
    val pdfTestSolucion=Thread.currentThread().contextClassLoader.getResource("2014TestSolucion.pdf").path
    val pdfConElTemario="C:\\Users\\davidTorre\\Desktop\\oposiciones\\temario\\Programa.pdf"
    //val pdfTest="C:\\Users\\davidTorre\\Desktop\\oposiciones\\examenes\\2014Test.pdf"
    //val pdfTestSolucion="C:\\Users\\davidTorre\\Desktop\\oposiciones\\examenes\\2014TestSolucion.pdf"
    //buscarBloques(obtenerCadenaPdf(pdfConElTemario))
    buscarTest("2014Int")
   // println(obtenerCadenaPdf(pdfTest));

}*/

fun buscarTest(anio:String){
    //println("${solucion}")
    val preguntas = obtenerTestAnio(anio);
    preguntas.forEach { println("${it.numero}- ${it.enunciado}")
        it.opciones!!.forEach { println("    ${it.apartado}) [${it.correcta}] ${it.solucion}") }
    }
}



fun buscarBloques(cadena:String):List<BloqueDto>{
    var valdev = estructuraElTemario(cadena)
    pintarElTemario(valdev)
    return valdev
}


