package com.selim.jokesapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.selim.jokesapp.R
import com.selim.jokesapp.databinding.JokeItemLayoutBinding
import com.selim.jokesapp.model.domain.Joke
import com.selim.jokesapp.ui.OnItemClicked

class JokesAdapter(private var list: List<Joke>,private val listener: OnItemClicked):RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    class JokesViewHolder(itemView: View) : ViewHolder(itemView) {
        val binding = JokeItemLayoutBinding.bind(itemView)
    }
    fun setData(list: List<Joke>){
        this.list =list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.joke_item_layout,parent,false)
        return JokesViewHolder(view)
    }

    override fun getItemCount() :Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val colors: Array<Int> = arrayOf(
            Color.parseColor("#FFA38F"),
            Color.parseColor("#FFCC8F"),
            Color.parseColor("#8FFFFA"),
            Color.parseColor("#A8FF8F"),
            Color.parseColor("#F8FF8F"),
            Color.parseColor("#8FACFF"),
            Color.parseColor("#D68FFF"),
            Color.parseColor("#FF8FB8"),
            Color.parseColor("#FF8F99"),
       )
        val currentJoke = list[position]
        val color = colors[position % colors.size]
        holder.binding.apply {
            jokeItemTvCategory.text = currentJoke.category+" joke"
            jokeItemTvPart1.text =currentJoke.setup
            jokeItemTvPart2.text =currentJoke.delivery
            jokeItemCardView.setCardBackgroundColor(color)
            jokeItemBtnCoby.setOnClickListener{
                listener.onItemClick(currentJoke)
            }
        }
    }
}