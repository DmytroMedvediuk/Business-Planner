package dmytrom.businessplanner.repository

import dmytrom.businessplanner.repository.database.entities.BusinessEvent

interface BusinessEventsRepository {

    suspend fun getAllEvents(): List<BusinessEvent>

    suspend fun addEvent(event: BusinessEvent)

    suspend fun updateEvent(event: BusinessEvent)

    suspend fun getEvent(id: Int): BusinessEvent?
}