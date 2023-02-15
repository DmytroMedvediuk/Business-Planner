package dmytrom.businessplanner.repository.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import dmytrom.businessplanner.data.EMPTY_STRING

@Entity(tableName = "Project")
data class Project(
    @PrimaryKey(true)
    val id: Int,
    val name: String = EMPTY_STRING,
    val description: String = EMPTY_STRING
)