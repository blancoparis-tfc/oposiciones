package aplication.dao

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

import javax.transaction.Transactional

/**
 * Created by davidTorre on 05/08/2016.
 */
@Entity
data class Oposicion(
    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    var id: Long?=0,
    var descripcion:String ="",
    var nivel:String = "",
    @OneToMany(cascade = kotlin.arrayOf(javax.persistence.CascadeType.ALL))
    var test:Set<Test>?=  HashSet<Test>(),
    @OneToMany(cascade = kotlin.arrayOf(javax.persistence.CascadeType.ALL))
    var plan:Set<Plan>?= HashSet<Plan>()
)

data class OposicionPlana(val idOposicion:Long,val descripcion:String
                          ,val anioTemario:String,val anioTest:String)



interface OposicionDao: CrudRepository<Oposicion, Long>{
    @Query("select new aplication.dao.OposicionPlana(op.id,op.descripcion,p.anio,t.periodo) " +
            " from Oposicion op left join op.test t left join op.plan p ")
    fun findByOposicion():List<OposicionPlana>
}


