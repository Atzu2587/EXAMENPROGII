package cl.miguel.registromedidoresapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mediciones")
data class Medicion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String, // agua, luz o gas
    val valor: Int,
    val fecha: String
)
