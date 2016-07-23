package aplication.componentes.pdf

import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor

/**
 * Created by davidTorre on 22/07/2016.
 */

 fun obtenerCadenaPdf( path: String,saltar:Number=0): String {
    println("Ruta: ${path}")
    val lector = PdfReader(path)
    val totalPaginas = lector.numberOfPages
    var documento = ""
    for (i in 1..totalPaginas) {
        if(i>saltar.toInt()) {
            documento += PdfTextExtractor.getTextFromPage(lector, i)
        }
    }
    return documento
}
