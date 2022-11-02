package co.aladinjunior.tfitstudio

import android.app.Application
import co.aladinjunior.tfitstudio.models.AppDatabase

class App : Application() {

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
    }
}