package cl.miguel.registromedidoresapp.data

import androidx.room.*
import cl.miguel.registromedidoresapp.model.Medicion
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(medicion: Medicion)

    @Query("SELECT * FROM mediciones ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<Medicion>>
}
