import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val authRepositoryModule = DI.Module("AuthRepositoryModule") {
    bind<AuthRepository>() with singleton {
        AuthRepositoryImpl()
    }
}