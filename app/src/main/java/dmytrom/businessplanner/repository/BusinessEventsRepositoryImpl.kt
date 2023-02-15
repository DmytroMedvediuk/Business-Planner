package dmytrom.businessplanner.repository

import dmytrom.businessplanner.repository.database.dao.EventDao
import dmytrom.businessplanner.repository.database.entities.BusinessEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BusinessEventsRepositoryImpl @Inject constructor(
    private val eventDao: EventDao
) : BusinessEventsRepository {
    override suspend fun getAllEvents(): List<BusinessEvent> {
        return withContext(Dispatchers.IO) {
            eventDao.getAll()
        }

    }

    override suspend fun addEvent(event: BusinessEvent) =
        withContext(Dispatchers.IO) { eventDao.insert(event) }

    override suspend fun updateEvent(event: BusinessEvent) = eventDao.update(event)

    override suspend fun getEvent(id: Int) = eventDao.getEventById(id)
}