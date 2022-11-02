package co.aladinjunior.tfitstudio.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fees::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feesDao(): FeesDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE?.let {
                INSTANCE as AppDatabase
            } ?: kotlin.run {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "tfit_studio"
                    ).build()
                }
                INSTANCE as AppDatabase
            }
        }
    }


}