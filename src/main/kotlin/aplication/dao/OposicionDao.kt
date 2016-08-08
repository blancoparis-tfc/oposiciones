package aplication.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.*
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
    var test:Set<Test>?=  HashSet<Test>()
)
interface OposicionDao: CrudRepository<Oposicion, Long>


