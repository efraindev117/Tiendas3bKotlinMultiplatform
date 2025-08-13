package di

import com.shared.di.KoinModules
import com.tienda3b.app.core.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoinDesktop(
    config: KoinAppDeclaration? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            KoinModules.modulesList
        )
    }
}
