import di.Inject
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton
import config.PlatformConfig

object PlatformSDK {

    fun init(
        configuration: PlatformConfig
    ) {
        val dependenciesModule = DI.Module(
            name = "dependencies",
            init = {
                bind<PlatformConfig>() with singleton { configuration }
            }
        )
        val map  = HashSet<Char>()

        Inject.createDependencies(
            DI {
                importAll(
                    dependenciesModule,
                    coreModule,
                    authRepositoryModule,
                )
            }.direct
        )
    }
}