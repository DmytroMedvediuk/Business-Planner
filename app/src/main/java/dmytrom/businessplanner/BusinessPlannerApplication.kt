package dmytrom.businessplanner

import android.app.Application
import dmytrom.businessplanner.di.BusinessApplicationModule
import dmytrom.businessplanner.di.BusinessPlannerApplicationComponent
import dmytrom.businessplanner.di.DaggerBusinessPlannerApplicationComponent

class BusinessPlannerApplication : Application() {

    lateinit var businessPlannerApplicationComponent: BusinessPlannerApplicationComponent

    override fun onCreate() {
        super.onCreate()

        businessPlannerApplicationComponent = DaggerBusinessPlannerApplicationComponent.builder()
            .appModule(BusinessApplicationModule(this)).build()

    }
}