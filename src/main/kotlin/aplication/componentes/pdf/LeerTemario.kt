package aplication.componentes.pdf

import java.util.*

/**
 * Created by davidTorre on 22/07/2016.
 */

data class Bloque(val bloque:String,val temas:List<Temario>)
data class Temario(var numero:String="",var tema:String="", var apartados:List<String>?= ArrayList());

val patroBloque = Regex("B[lL][oO][Qq][uU][eE][sS]? [IV]+")
val patronTema = Regex("\\d+\\.\\-")
fun leerTemario(anio:String): ArrayList<Bloque>{
    var pdfTemario=Thread.currentThread().contextClassLoader.getResource("Temario${anio}.pdf").path
    return estructuraElTemario(obtenerCadenaPdf(pdfTemario,0));
}

fun estructuraElTemario(cadena: String): ArrayList<Bloque> {
    val valdev = ArrayList<Bloque>()
    var tema: Temario = Temario();
    var temas = ArrayList<Temario>()
    var bloque: Bloque? = null
    val lineas = cadena.lines().filter { !it.isBlank() }.toList();
    for (it in lineas) {
        if (patroBloque.containsMatchIn(it)) {
            if (bloque != null) {
                valdev.add(bloque);
            }
            establecerTema(patronTema, tema, temas)
            temas = ArrayList<Temario>();
            bloque = Bloque(it, temas);
            tema = Temario()
        } else if (patronTema.containsMatchIn(it)) {
            establecerTema(patronTema, tema, temas)
            tema = Temario("", it);
        } else {
            tema.tema += it
        }
    }
    establecerTema(patronTema, tema, temas)
    if (bloque != null) {
        valdev.add(bloque);
    }
    return valdev
}

fun pintarElTemario(valdev: ArrayList<Bloque>) {
    valdev.forEach {
        println("${it.bloque}")
        it.temas.forEach {
            println("  ${it}");
            it.apartados?.forEach {
                println("       - ${it}");2
            }
        }
    }
}

private fun establecerTema(patronTema: Regex, tema: Temario, temas: ArrayList<Temario>) {
    if (!tema.tema.isBlank()) {
        tema.numero=(patronTema.find(tema.tema)?.value?:"").replace(".-","");
        tema.tema=tema.tema.replace(patronTema, "");
        tema.apartados = tema.tema.split(". ").filter { !it.isBlank() }.map { it.trim() + "." };
        temas.add(tema);
    }
}