package dmytrom.businessplanner.repository

import dmytrom.businessplanner.repository.database.entities.BusinessEvent
import javax.inject.Inject

class MockedBusinessEventsRepository @Inject constructor() : BusinessEventsRepository {

    private val events = mutableListOf(
        BusinessEvent("Name 1", "Description 1"),
        BusinessEvent("Name 2", "Description 2"),
        BusinessEvent("Name 3", "Description 3")
    )

    override suspend fun getAllEvents() = events

    override suspend fun addEvent(event: BusinessEvent) {
        events.add(event)
    }

    override suspend fun updateEvent(event: BusinessEvent) {
        events
            .indexOfFirst { it.id == event.id }
            .takeIf { it != -1 }
            ?.also {
                events[it] = event
            }

    }

    override suspend fun getEvent(id: Int) = events.find { it.id == id }
}