package aplication.componentes.pdf

import aplication.dao.Apartado
import aplication.dao.Pregunta
import org.springframework.util.StringUtils
import java.util.*

/**
 * Created by davidTorre on 22/07/2016.
 */
data class PreguntaDto(var id:Long,var bloque:String="", var numero:Long=-1L, var enunciado:String="", var anulada:Boolean=false, var opciones:ArrayList<Opciones>?=null){
    constructor() : this(-1,"",-1L,"",false,null) {
    }
    constructor(numero:Long,enuncionado:String,anulada:Boolean):this(-1,"",numero,enuncionado,anulada,null)
    constructor(pregunta: Pregunta):this(-1,"Pendiente",pregunta.numero,pregunta.enunciado,pregunta.anulado,null)
}
data class Opciones(val id:Long=-1,val apartado:String="",var solucion:String="",var correcta:Boolean=false)
private enum class EstadoLectura{
    NADA,BLOQUE,PREGUNTA,APARTADO,APARTADO_FIN
}
private enum class Apartados{
    a,b,c,d,e,f,g,h,i,j,k,m,n
}
val patronPregunta = Regex("^\\d+\\. ")
var patronApartado=Regex("^[a-z]\\)")
var patronSubApartado=Regex(" [a-z]\\)")
                      //   1 – TEST 2011 – GSI PI  Página 11 de 13

//^1 [-–] TEST \d+ [-–] GSI [LP][I]?\s+Página \d+ de \d+
var patronPagina=Regex("^1 [–-] TEST \\d+ [–-] GSI [LP][I]?\\s+Página \\d+ de \\d+")
var patronRespuesta=Regex("\\d+\\.\\s+\\w+")
var patronSolucion=Regex("\\. [ABCD]");

fun obtenerTestAnio(anio:String): List<PreguntaDto>{
    var pdfTest=Thread.currentThread().contextClassLoader.getResource("${anio}Test.pdf").path
    val pdfTestSolucion=Thread.currentThread().contextClassLoader.getResource("${anio}TestSolucion.pdf").path
    return obtenerTest(pdfTest,pdfTestSolucion)
}

 fun obtenerTest(pdfTest: String, pdfTestSolucion: String): List<PreguntaDto> {
    val preguntas = soluciones(obtenerCadenaPdf(pdfTestSolucion, 0), cuestionario(obtenerCadenaPdf(pdfTest, 1)));
    return preguntas
}

fun soluciones(cadena:String,preguntas:List<PreguntaDto>):List<PreguntaDto>{
    var columna1=ArrayList<String>();
    val columna2=ArrayList<String>();
    var columna3=ArrayList<String>();

    val soluciones=organizarSoluciones(cadena)
    var reserva=ArrayList<String>();
    var i = 0;
    while (i+3 < soluciones.size) {
        val col1 = soluciones.get(i)
        val col2 = soluciones.get(i + 1)
        val col3 = soluciones.get(i + 2)
        val ncol1 = patronPregunta.find(soluciones.get(i))?.value;
        val ncol2 = patronPregunta.find(soluciones.get(i + 1))?.value;
        val ncol3 = patronPregunta.find(soluciones.get(i + 2))?.value;
        //println("${col1} ${col2} ${col3}")
        if (ncol1.equals(ncol2) && ncol1.equals(ncol3)) {
            columna1.add(col1)
            columna2.add(col2)
            columna3.add(col3)
        }else{
            reserva.add(col1)
            reserva.add(col2)
            reserva.add(col3)
        }
        i += 3;
    }
    //i++;
    while(i < soluciones.size){
        reserva.add(soluciones.get(i))
        i++;
    }
    i=0;
    while(i+1<reserva.size){
        columna2.add(reserva.get(i))
        columna3.add(reserva.get(i+1))
        i+=2;
    }

    val bloques=preguntas.filter { !it.bloque.isBlank() }.map{ it.bloque }.distinct().toList();
    println(bloques)
    val bloque1=preguntas.filter { it.bloque.equals(bloques.get(0)) }.toList();
    val bloque2=preguntas.filter { it.bloque.equals(bloques.get(1)) }.toList();
    val bloque3=preguntas.filter { it.bloque.equals(bloques.get(2)) }.toList();

    vincularSoluciones(bloque1, columna1)
    vincularSoluciones(bloque2, columna2)
    vincularSoluciones(bloque3, columna3)
    return preguntas.filter { !it.bloque.isBlank() };
}

private fun vincularSoluciones(bloque1: List<PreguntaDto>, columna1: ArrayList<String>) {

    var i1 = 0;
    while (i1 < bloque1.size) {
        var opcion = patronSolucion.find(columna1.get(i1))?.value
        if (opcion != null) {
            opcion=opcion.replace(".","").trim().toLowerCase();
            bloque1.get(i1).opciones!!.filter { it.apartado.equals(opcion) }.forEach { it.correcta = true }
        } else {
            bloque1.get(i1).anulada = true;
        }
        i1++;
    }
}

private fun organizarSoluciones(cadena: String):ArrayList<String> {
    val soluciones = patronRespuesta.findAll(cadena).toList().map { it.value }.toCollection(ArrayList<String>());
    var i = 0;
    while (i+3 < soluciones.size) {

        val col1 = patronPregunta.find(soluciones.get(i))?.value;
        val col2 = patronPregunta.find(soluciones.get(i + 1))?.value;
        val col3 = patronPregunta.find(soluciones.get(i + 2))?.value;
        println("${col1} ${col2} ${col3}")
        if (!col1.equals(col2) || !col1.equals(col3)) {

            var j = i + 3;
            var intercambio = -1;
            while (j < soluciones.size && intercambio == -1) {
                var aux = patronPregunta.find(soluciones.get(j))?.value
                //println("${aux}");
                if (col1.equals(aux.toString())) {
                    intercambio = j;
                }
                j++;
            }
            //println("Salto (${i},${intercambio})")
            if (intercambio != -1) {
                soluciones.add(i, soluciones.removeAt(intercambio));
            }
        }
        i += 3;
    }
    return soluciones;
}

fun cuestionario(cadena:String):List<PreguntaDto>{
    val valdev=ArrayList<PreguntaDto>()
    val lineas = cadena.lines().filter { !it.isBlank() }
            .map{ it.replace(patronPagina,"").trim()+" "}
            //.filter { !patronPagina.containsMatchIn(it) }
            .toList()
    var pregunta= PreguntaDto(-1,"",-1,"",false,ArrayList<Opciones>())
    var opcion=Opciones()
    var estado=EstadoLectura.NADA
    var bloque = ""
    for (linea in lineas){
        if(patroBloque.containsMatchIn(linea)){
            bloque=linea
            estado=EstadoLectura.BLOQUE;
        }else if(patronPregunta.containsMatchIn(linea)){
            pregunta = establecerPregunta(bloque, estado, linea, opcion, pregunta, valdev)
            estado=EstadoLectura.PREGUNTA;
        }else if(patronApartado.containsMatchIn(linea)){
            if(estado.equals(EstadoLectura.APARTADO)){
                pregunta.opciones!!.add(opcion);
            }
            if(patronSubApartado.containsMatchIn(linea)){
                var apartados=linea.split(patronSubApartado)
                for(apartado in apartados){
                    if(estado.equals(EstadoLectura.APARTADO) && (pregunta.opciones!!.isEmpty() || pregunta.opciones!!.last().solucion!=opcion.solucion)) {
                        pregunta.opciones!!.add(opcion);

                    }
                    opcion=Opciones(-1,"",apartado.replace(patronApartado,""))
                    estado=EstadoLectura.APARTADO
                }

            }else{
                estado=EstadoLectura.APARTADO
                opcion=Opciones(-1,"",linea.replace(patronApartado,""));
            }
        }else{
            when(estado){
                EstadoLectura.PREGUNTA->{
                    pregunta.enunciado+=linea;
                }
                EstadoLectura.APARTADO->{
                    opcion.solucion+=linea;
                }
            }
        }
    }
    establecerPregunta(bloque, estado, "", opcion, pregunta, valdev)
    return valdev;
}

private fun establecerPregunta(bloque: String, estado: EstadoLectura, linea: String, opcion: Opciones, pregunta: PreguntaDto, valdev: ArrayList<PreguntaDto>): PreguntaDto {
    var pregunta1 = pregunta
    if (!estado.equals(EstadoLectura.NADA)) {
        if (estado.equals(EstadoLectura.APARTADO)) {
            pregunta1.opciones!!.add(opcion);
        }
        pregunta1.opciones = pregunta1.opciones!!.mapIndexed { i, opciones -> Opciones(-1,Apartados.values().get(i).name, opciones.solucion) }.toCollection(ArrayList<Opciones>());
        valdev.add(pregunta1);
    }
    if(!linea.isBlank()){
    pregunta1 = PreguntaDto(-1,bloque, (patronPregunta.find(linea)?.value ?: "").replace(".", "").trim().toLong(), linea.replace(patronPregunta, ""),false, ArrayList<Opciones>());
    }
    return pregunta1
}