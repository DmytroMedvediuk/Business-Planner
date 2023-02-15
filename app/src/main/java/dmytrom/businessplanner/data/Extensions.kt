package dmytrom.businessplanner.data

import android.content.Context
import dmytrom.businessplanner.BusinessPlannerApplication
import dmytrom.businessplanner.di.BusinessPlannerApplicationComponent

val Context.appComponent: BusinessPlannerApplicationComponent
    get() = when (this) {
        is BusinessPlannerApplication -> businessPlannerApplicationComponent
        else -> this.applicationContext.appComponent
    }
