package cl.miguel.registromedidoresapp.ui
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.miguel.registromedidoresapp.R
import cl.miguel.registromedidoresapp.databinding.FragmentListadoBinding
import cl.miguel.registromedidoresapp.viewmodel.MedicionViewModel

class ListadoFragment : Fragment() {

    private var _binding: FragmentListadoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MedicionViewModel by viewModels()
    private lateinit var adapter: MedicionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListadoBinding.inflate(inflater, container, false)

        adapter = MedicionAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.listaMediciones.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.btnAgregar.setOnClickListener {
            findNavController().navigate(R.id.formularioFragment)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
