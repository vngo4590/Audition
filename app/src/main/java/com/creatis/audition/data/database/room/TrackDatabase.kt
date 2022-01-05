package com.creatis.audition.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.creatis.audition.data.database.room.dao.ImageDao
import com.creatis.audition.data.database.room.dao.RelationDao
import com.creatis.audition.data.database.room.dao.ShareDao
import com.creatis.audition.data.database.room.dao.TrackDao
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel

@Database(
    version = 1,
    entities = [TrackModel::class, ImagesModel::class, ShareModel::class],
    exportSchema = true,
    /*autoMigrations = [
        AutoMigration (
            from = 1,
            to = 2,
            spec = AppDatabase.MyAutoMigration::class
        )
    ]*/
)
@TypeConverters(Converters::class)
abstract class TrackDatabase : RoomDatabase() {
    /*abstract val imageDao: ImageDao
    abstract val shareDao: ShareDao
    abstract val trackDao: TrackDao*/
    abstract val relationDao : RelationDao

    companion object {
        @Volatile
        private var INSTANCE: TrackDatabase? = null

        fun getDatabase(context: Context): TrackDatabase {
            /*
            * Since multiple threads can ask for the database at the same time, we use
            * synchronisation to ensure that the database should only be requested once.
            * */
            synchronized(this) {
                var instance = INSTANCE
                // If null, we know that the db has not been set up yet
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            TrackDatabase::class.java,
                            "track_database"
                        )
                            // database to be cleared when you upgrade the version
                            // Need this here for awhile because DB set up has not
                            // been not completed yet
                            .fallbackToDestructiveMigration()
                            .build()
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }

    }

}