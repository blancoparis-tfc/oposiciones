package aplication.componentes.md

import java.util.*

/**
 * Created by davidTorre on 16/08/2016.
 */
enum class TipoMd{PARRAFO,COMENTARIO,ENCABEZADO,CODIGO}

data class TokenMd(val tipo:TipoMd,val texto:String)



fun procesarMd(cadena:String):List<TokenMd>{
    val lineas=cadena.lines().map { it.trim() };
    var aux=""
    val valdev=ArrayList<TokenMd>();
    lineas.forEach {
        if(it.isBlank() && aux.isNotBlank()){
            valdev.add(TokenMd(TipoMd.PARRAFO,aux))
            aux="";
        }else if(aux.isBlank()){
            aux+=it;
        }else{
            aux+=" ${it}";
        }
    }
    return valdev;
}