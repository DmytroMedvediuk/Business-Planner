package dmytrom.businessplanner.repository.database.dao

import androidx.room.*
import dmytrom.businessplanner.repository.database.entities.BusinessEvent

@Dao
interface EventDao {

    @Query("SELECT * FROM business_event")
    fun getAll(): List<BusinessEvent>

    @Insert
    fun insert(event: BusinessEvent)

    @Delete
    fun delete(event: BusinessEvent)

    @Update
    fun update(event: BusinessEvent)

    @Query("SELECT * FROM business_event WHERE id = :id")
    fun getEventById(id: Int): BusinessEvent?
}