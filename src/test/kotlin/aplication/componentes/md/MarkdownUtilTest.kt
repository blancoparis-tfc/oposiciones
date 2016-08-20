package aplication.componentes.md
import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.CoreMatchers.*

/**
 * Created by davidTorre on 16/08/2016.
 */

class MardownUtilsTest{
    @Test
    fun testSoloParrafos(){
        var texto="""

        Esto es el primer parrafoa


        Esto es el segundo parrafo
        de dos lineas.

        """
        val resultado=procesarMd(texto);

        assertThat("Miramos que esten los elementos",resultado, hasSize(2))
        assertThat("Miramo si estan los textos",resultado,
                contains(
                        hasProperty("texto",org.hamcrest.CoreMatchers.`is`("Esto es el primer parrafo"))
                        ,hasProperty("texto",org.hamcrest.CoreMatchers.`is`("Esto es el segundo parrafo de dos lineas."))
                )
        )
        assertThat("Miramo los tipos",resultado,
                contains(
                        hasProperty("tipo",org.hamcrest.CoreMatchers.`is`(TipoMd.PARRAFO))
                        ,hasProperty("tipo",org.hamcrest.CoreMatchers.`is`(TipoMd.PARRAFO))
                )
        )
    }

    @Test
    fun testCabeceraParrafos(){
        var texto="""
        # Titulo 1
        parrafo pegado
        en dos lineas

        segundo parrafo
        # Titulo 2

        parrafo del segundo titulo

        """

        val resultado=procesarMd(texto);


        assertThat("Miramos que esten los elementos",resultado, hasSize(5))
        assertThat("Miramo si estan los textos",resultado,
                contains(
                        hasProperty("texto",org.hamcrest.CoreMatchers.`is`("Titulo 1"))
                        ,hasProperty("texto",org.hamcrest.CoreMatchers.`is`("parrafo pegado en dos lineas"))
                        ,hasProperty("texto",org.hamcrest.CoreMatchers.`is`("segundo parrafo"))
                        ,hasProperty("texto",org.hamcrest.CoreMatchers.`is`("Titulo 2"))
                        ,hasProperty("texto",org.hamcrest.CoreMatchers.`is`("parrafo del segundo titulo"))
                )
        )
        assertThat("Miramo los tipos",resultado,
                contains(
                        hasProperty("tipo",org.hamcrest.CoreMatchers.`is`(TipoMd.ENCABEZADO))
                        ,hasProperty("tipo",org.hamcrest.CoreMatchers.`is`(TipoMd.PARRAFO))
                        ,hasProperty("tipo",org.hamcrest.CoreMatchers.`is`(TipoMd.PARRAFO))
                        ,hasProperty("tipo",org.hamcrest.CoreMatchers.`is`(TipoMd.ENCABEZADO))
                        ,hasProperty("tipo",org.hamcrest.CoreMatchers.`is`(TipoMd.PARRAFO))

                )
        )
    }
}