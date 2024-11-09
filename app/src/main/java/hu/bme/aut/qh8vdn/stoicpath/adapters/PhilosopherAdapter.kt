package hu.bme.aut.qh8vdn.stoicpath.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.qh8vdn.stoicpath.R
import hu.bme.aut.qh8vdn.stoicpath.data.Philosopher
import hu.bme.aut.qh8vdn.stoicpath.databinding.ItemPhilosopherBinding
import hu.bme.aut.qh8vdn.stoicpath.fragments.PhilosophersFragmentDirections

class PhilosopherAdapter(
    private val philosophers: List<Philosopher>,
    private val onFavoriteClick: (Philosopher) -> Unit
) : RecyclerView.Adapter<PhilosopherAdapter.PhilosopherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhilosopherViewHolder {
        val binding = ItemPhilosopherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhilosopherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhilosopherViewHolder, position: Int) {
        val philosopher = philosophers[position]
        holder.bind(philosopher)
        holder.itemView.setOnClickListener {
            val action = PhilosophersFragmentDirections.actionPhilosophersToDetail(philosopher.id)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = philosophers.size

    inner class PhilosopherViewHolder(
        private val binding: ItemPhilosopherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(philosopher: Philosopher) {
            // Set philosopher details
            binding.philosopherName.text = binding.root.context.getString(philosopher.nameResId)
            binding.philosopherImage.setImageResource(philosopher.imageResId)

            // Set initial star icon based on isFavorite status
            binding.favoriteIcon.setImageResource(
                if (philosopher.isFavorite) R.drawable.ic_filled_star else R.drawable.ic_empty_star
            )

            // Handle favorite icon click
            binding.favoriteIcon.setOnClickListener {
                // Call onFavoriteClick to update data and shared preferences
                onFavoriteClick(philosopher)

                // Update the icon immediately to reflect the change in isFavorite status
                binding.favoriteIcon.setImageResource(
                    if (philosopher.isFavorite) R.drawable.ic_filled_star else R.drawable.ic_empty_star
                )
            }
        }
    }
}

