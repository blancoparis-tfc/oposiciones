package aplication.dao

import org.hibernate.annotations.Type
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*
import javax.persistence.*


/**
 * Created by davidTorre on 09/08/2016.
 */
@Entity
data class Plan(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id:Long?=null,
        var anio:String="",
        @OneToMany(cascade = kotlin.arrayOf(javax.persistence.CascadeType.ALL))
        var bloques:List<Bloque>?=null)

@Entity
data class Bloque(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) val id:Long?=null,
        var descripcion:String="",
        @OneToMany(cascade = kotlin.arrayOf(javax.persistence.CascadeType.ALL))
        var temas:List<Tema>?=null)
data class BloquePlano(val idBloque:Long=-1,val descripcionBloque:String="",
        val idTema:Long=-1,val numeroTema:String="",val tema:String="")
interface BloqueDao:CrudRepository<Bloque,Long>{
        @Query("select new aplication.dao.BloquePlano(b.id,b.descripcion,t.id,t.numero,t.tema) " +
                " from Plan p left join p.bloques b left join b.temas t " +
                " where p = :plan")
        fun findByPlan(@Param("plan")plan:Plan):List<BloquePlano>
}

@Entity
data class Tema(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id:Long?=null,
        var numero:String="",
        @Column(name="solucion",length=2059)
        var tema:String="",
        @ElementCollection()
        var apartados: List<String>?=ArrayList<String>(),
        @OneToMany(cascade = kotlin.arrayOf(javax.persistence.CascadeType.ALL))
        var documentos:List<Documentos>?=ArrayList<Documentos>()
        )

@Entity
data class Documentos(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) var id:Long?=null,
        var etiqueta:String="",
        @Type(type="text")
        var documento:String="")

