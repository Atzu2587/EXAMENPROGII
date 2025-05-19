package cl.miguel.registromedidoresapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cl.miguel.registromedidoresapp.R
import cl.miguel.registromedidoresapp.databinding.ItemMedicionBinding
import cl.miguel.registromedidoresapp.model.Medicion

class MedicionAdapter : ListAdapter<Medicion, MedicionAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemMedicionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(medicion: Medicion) {
            binding.tvTipo.text = medicion.tipo.uppercase()
            binding.tvValor.text = "${medicion.valor}"
            binding.tvFecha.text = medicion.fecha

            val iconoRes = when (medicion.tipo.lowercase()) {
                "agua" -> R.drawable.icono_agua
                "luz" -> R.drawable.icono_luz
                "gas" -> R.drawable.icono_gas
                else -> R.drawable.icono_agua
            }
            binding.ivIcono.setImageResource(iconoRes)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMedicionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class DiffCallback : DiffUtil.ItemCallback<Medicion>() {
        override fun areItemsTheSame(oldItem: Medicion, newItem: Medicion): Boolean =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Medicion, newItem: Medicion): Boolean =
            oldItem == newItem
    }
}
