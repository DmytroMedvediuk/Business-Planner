package dmytrom.businessplanner.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dmytrom.businessplanner.repository.database.dao.EventDao
import dmytrom.businessplanner.repository.database.entities.BusinessEvent
import dmytrom.businessplanner.repository.database.entities.Project

@Database(
    version = 2,
    entities = [BusinessEvent::class, Project::class],
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "business_planner_database"

        internal val migrate_1_2 = migrate(1, 2) {
            execSQL(
                """ 
                   INSERT INTO business_event (name, description)
                   VALUES
                            ("Event name 1", "Event Description 1"),
                            ("Event name 2", "Event Description 2"),
                            ("Event name 3", "Event Description 3"),
                            ("Event name 4", "Event Description 4");
                """
            )
        }

        private inline fun migrate(
            from: Int,
            to: Int,
            crossinline transaction: SupportSQLiteDatabase.() -> Unit
        ) =
            object : Migration(from, to) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.transaction()
                }
            }
    }

    abstract fun eventDao(): EventDao
}