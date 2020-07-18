package com.oliverdumhart.plantcare.main.plantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oliverdumhart.plantcare.databinding.ListItemPlantBinding
import com.oliverdumhart.plantcare.models.Plant

class PlantListAdapter(val clickListener: PlantClickListener) : ListAdapter<Plant, PlantListAdapter.ViewHolder>(PlantListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }


    class ViewHolder private constructor(val binding: ListItemPlantBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(clickListener: PlantClickListener, item: Plant){
            binding.plant = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPlantBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class PlantClickListener(val clickListener: (plant: Plant) -> Unit) {
    fun onClick(plant: Plant) = clickListener(plant)
}

class PlantListDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean = (oldItem == newItem)
}