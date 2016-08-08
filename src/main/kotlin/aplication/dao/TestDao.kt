package aplication.dao

import aplication.componentes.pdf.PreguntaDto
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import javax.persistence.*

/**
 * Created by davidTorre on 05/08/2016.
 */
@Entity
data class Test(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
        var id: Long?=null,
        var periodo:String="",
        //@ManyToOne
        //var oposicion:Oposicion?=null,
        @OneToMany(cascade = arrayOf(javax.persistence.CascadeType.ALL))
        var preguntas:List<Pregunta>?=null
)
interface TestDao: CrudRepository<Test,Long>
@Entity
data class Pregunta(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
        var id: Long?=null,
        var numero: Long=0,
        @Column(name="enunciado",length=2059)
        var enunciado: String="",
        var anulado: Boolean=false,
       // @ManyToOne
      //  var test:Test?=null,
        @OneToMany(cascade = kotlin.arrayOf(javax.persistence.CascadeType.ALL))
        var apartados:List<Apartado>?=null
)

data class PreguntaPlano(
        val id:Long=-1, val numero:Long=-1, val enunciado:String="", val anulado:Boolean=false
        ,val idApartado:Long=-1, val apartado:String="",val solucion:String="",val correcto:Boolean
)

interface PreguntaDao: CrudRepository<Pregunta,Long>{
       @Query("select new aplication.dao.PreguntaPlano(t.id,t.numero,t.enunciado,t.anulado,a.id,a.apartado,a.solucion,a.correcto) " +
            " from Test ts left join ts.preguntas t left join t.apartados a "
           +" where ts = :test")
       fun findByTest(@Param("test")test:Test):List<PreguntaPlano>
}
@Entity
data class Apartado(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
        var id: Long?=null,
        var apartado:String="",
        @Column(name="solucion",length=2059)
        var solucion:String="",
        var correcto:Boolean=false
//        @ManyToOne
//        var pregunta:Pregunta?=null


)
interface ApartadoDao: CrudRepository<Apartado,Long>{}

