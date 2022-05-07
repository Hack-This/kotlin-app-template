package uk.hackthis.api

import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import org.slf4j.LoggerFactory
import uk.hackthis.config.Config
import uk.hackthis.logger

class AdminApi(private val config: Config) {

    private val logger = logger<AdminApi>()

    fun validateToken(ctx: Context){
        if(config.getAdminToken() != ctx.header("token")){
            logger.warn("Unauthorised request received at ${ctx.path()}")
            throw NotFoundResponse()
        }
    }

}