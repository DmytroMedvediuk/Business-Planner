package dmytrom.businessplanner.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dmytrom.businessplanner.repository.BusinessEventsRepository
import dmytrom.businessplanner.ui.MainViewModel
import javax.inject.Inject
import javax.inject.Provider

class BusinessPlannerViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MainViewModel(repository) as T

        var creator = viewModels[modelClass]
        creator = creator
            ?: viewModels.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
        creator ?: throw IllegalArgumentException("model class $modelClass not found")
        return creator.get() as T
    }
}