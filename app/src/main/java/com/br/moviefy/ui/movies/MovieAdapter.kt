package com.br.moviefy.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.moviefy.data.model.Movie
import com.br.moviefy.databinding.ItemMovieBinding
import com.br.moviefy.util.loadImage

class MovieAdapter (private val movies: List<Movie>,
                    private val onItemClick: ((movie: Movie) -> Unit)): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private var dataMovies = mutableListOf<Movie?>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding, onItemClick)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    inner class MovieViewHolder(private val binding : ItemMovieBinding, private val onItemClick: (movie: Movie) -> Unit): RecyclerView.ViewHolder(binding.root){

        fun bindView(movie: Movie) {

            binding.moviePosterImageView.loadImage(movie.poster_path)
            binding.titleTextView.text = movie.title
            binding.overviewTextView.text = movie.overview



            itemView.setOnClickListener{
                onItemClick.invoke(movie)
            }
        }

    }

    fun updateData(newData : List<Movie?>){
        dataMovies.clear()
        dataMovies.addAll(newData)
        notifyDataSetChanged()
    }




}