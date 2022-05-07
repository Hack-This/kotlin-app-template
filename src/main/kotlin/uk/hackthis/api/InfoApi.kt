package uk.hackthis.api

import io.javalin.http.Context
import uk.hackthis.api.dto.ServiceInfoResponse
import uk.hackthis.config.Config

class InfoApi(private val config: Config) {

    fun getInfo(ctx: Context) {
        ctx.json(
            ServiceInfoResponse(
            name = config.getServiceName(),
            version = config.getServiceVersion()
        )
        )
    }

}