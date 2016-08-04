package aplication.bom

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by davidTorre on 04/08/2016.
 */
@Entity
class Prueba(
        var campo: String="",
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long=0
)