package dmytrom.businessplanner.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dmytrom.businessplanner.BusinessPlannerApplication
import dmytrom.businessplanner.repository.BusinessEventsRepository
import dmytrom.businessplanner.repository.BusinessEventsRepositoryImpl
import dmytrom.businessplanner.repository.MockedBusinessEventsRepository
import dmytrom.businessplanner.repository.database.AppDataBase
import dmytrom.businessplanner.repository.database.dao.EventDao
import dmytrom.businessplanner.ui.MainViewModel

@Module(includes = [RepositoryModule::class, ViewModelModule::class])
class BusinessApplicationModule(private val application: BusinessPlannerApplication) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideAppDataBase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .addMigrations(AppDataBase.migrate_1_2)
            .build()

    @Provides
    fun provideBusinessEventDao(db: AppDataBase): EventDao = db.eventDao()
}

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(businessEventsRepositoryImpl: BusinessEventsRepositoryImpl): BusinessEventsRepository
}

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: BusinessPlannerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindCheckoutViewModel(viewModel: MainViewModel): ViewModel
}
