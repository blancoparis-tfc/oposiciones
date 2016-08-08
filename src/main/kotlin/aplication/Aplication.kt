package aplication

import aplication.service.OposicionesService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by davidTorre on 19/07/2016.
 */
@SpringBootApplication
@ComponentScan
@Configuration
@EnableAutoConfiguration
open class Application {

    val logger = LoggerFactory.getLogger(Application.javaClass);

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
    @Bean
    open fun initDatos(oposicionService: OposicionesService)=CommandLineRunner{
        logger.info("Cargar los datos")
        oposicionService.initOposicion()
        /*var oposicion =oposicionDao.findOne(oposicionMock);
        if(oposicion==null){
            logger.info("tenemos que crear la oposicion")
            oposicionDao.save(Oposicion(null,"Informatica (Ministerio)","A2"))
        }*/
    }
}