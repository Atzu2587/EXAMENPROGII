package cl.miguel.registromedidoresapp.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cl.miguel.registromedidoresapp.R
import cl.miguel.registromedidoresapp.databinding.FragmentFormularioBinding
import cl.miguel.registromedidoresapp.viewmodel.MedicionViewModel
import java.util.*

class FormularioFragment : Fragment() {

    private var _binding: FragmentFormularioBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MedicionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormularioBinding.inflate(inflater, container, false)

        // Abrir selector de fecha con formato AAAA-MM-DD
        binding.etFecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(requireContext(), { _, y, m, d ->
                val fechaFormateada = String.format("%04d-%02d-%02d", y, m + 1, d)
                binding.etFecha.setText(fechaFormateada)
            }, year, month, day)

            datePicker.show()
        }
        binding.btnGuardar.setOnClickListener {
            val valor = binding.etValor.text.toString().toIntOrNull()
            val fecha = binding.etFecha.text.toString()

            val tipo = when (binding.rgMedidores.checkedRadioButtonId) {
                R.id.rbAgua -> "agua"
                R.id.rbLuz -> "luz"
                R.id.rbGas -> "gas"
                else -> ""
            }
            if (valor != null && fecha.isNotBlank() && tipo.isNotEmpty()) {
                viewModel.agregarMedicion(tipo, valor, fecha)
                findNavController().navigate(R.id.listadoFragment)
            } else {
                // aca podria agregar algun mensaje de ser necesario
            }
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
