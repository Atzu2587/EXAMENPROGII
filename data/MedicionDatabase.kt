package cl.miguel.registromedidoresapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.miguel.registromedidoresapp.model.Medicion

@Database(entities = [Medicion::class], version = 1, exportSchema = false)
abstract class MedicionDatabase : RoomDatabase() {
    abstract fun medicionDao(): MedicionDao

    companion object {
        @Volatile private var INSTANCE: MedicionDatabase? = null

        fun obtenerInstancia(context: Context): MedicionDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MedicionDatabase::class.java,
                    "mediciones_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
