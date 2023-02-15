package dmytrom.businessplanner.di

import dagger.Component
import dmytrom.businessplanner.ui.MainActivity

@Component(modules = [BusinessApplicationModule::class])
interface BusinessPlannerApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        fun appModule(module: BusinessApplicationModule): Builder

        fun build(): BusinessPlannerApplicationComponent
    }
}