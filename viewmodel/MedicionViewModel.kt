package cl.miguel.registromedidoresapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import cl.miguel.registromedidoresapp.data.MedicionDatabase
import cl.miguel.registromedidoresapp.model.Medicion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicionViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MedicionDatabase.obtenerInstancia(application).medicionDao()
    val listaMediciones = dao.obtenerTodas().asLiveData()

    fun agregarMedicion(tipo: String, valor: Int, fecha: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertar(Medicion(tipo = tipo, valor = valor, fecha = fecha))
        }
    }
}
