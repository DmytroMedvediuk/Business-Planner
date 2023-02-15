package dmytrom.businessplanner.repository.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_event")
data class BusinessEvent(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}