package uk.hackthis.cli

import uk.hackthis.api.RouteController
import uk.hackthis.logger

class AppRunner(private val routeController: RouteController) {

    private val logger = logger<AppRunner>()

    fun run(args: List<String>){
        logger.info("Starting app")
        routeController.start()
    }

}