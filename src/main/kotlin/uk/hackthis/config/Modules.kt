package uk.hackthis.config

import uk.hackthis.cli.AppRunner
import org.koin.dsl.module
import org.ktorm.database.Database
import org.ktorm.support.postgresql.PostgreSqlDialect
import uk.hackthis.api.AdminApi
import uk.hackthis.api.ExampleApi
import uk.hackthis.api.InfoApi
import uk.hackthis.api.RouteController
import uk.hackthis.api.error.ErrorHandler
import java.time.Clock
import kotlin.random.Random

object Modules {

    private val utils = module {
        single { AppRunner(get()) }
        single { Config() }
        single<Clock> { Clock.systemDefaultZone() }
        single<Random> { Random.Default }
    }

    private val apis = module {
        single { AdminApi(get()) }
        single { ExampleApi() }
        single { InfoApi(get()) }
        single { RouteController(get(), get(), get(), get(), get()) }
        single { ErrorHandler() }
    }

    private val daos = module {
        single { setupDatabase(get()) }
    }

    private fun setupDatabase(config: Config): Database {
        return Database.connect(
            url = config.getDatabaseUrl(),
            driver = config.getDatabaseDriver(),
            user = config.getDatabaseUsername(),
            password = config.getDatabasePassword(),
            dialect = PostgreSqlDialect()
        )
    }

    internal val allModules = listOf(
        utils,
        apis
    )

}