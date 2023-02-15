package dmytrom.businessplanner.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dmytrom.businessplanner.repository.BusinessEventsRepository
import dmytrom.businessplanner.repository.database.entities.BusinessEvent
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class MainViewModel @Inject constructor(
    private val repository: BusinessEventsRepository
) : ViewModel() {

    private val _events = MutableLiveData<List<BusinessEvent>>()
    val events: LiveData<List<BusinessEvent>> = _events

    fun getAllEvents() {
        viewModelScope.launch {
            _events.value = repository.getAllEvents()
        }
    }

    fun addEvent() {
        viewModelScope.launch {
            val rand = Random(10000).nextInt()
            repository
                .addEvent(BusinessEvent("new event $rand", "new description $rand"))

            getAllEvents()
        }
    }
}
