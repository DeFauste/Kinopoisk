package com.example.kinopoisk.bookmarksFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.R
import com.example.kinopoisk.database.MoviesData
import com.example.kinopoisk.databinding.ItemMovieBinding


class RecyclerAdapterBookmarksMovie(private val onClickListenerMovie: onClickListenerBookmarksMovie) :
    RecyclerView.Adapter<RecyclerAdapterBookmarksMovie.MovieViewHolder>() {

    class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesData, onClickListenerMovie: onClickListenerBookmarksMovie) {
            with(binding) {
                nameMovie.text = movie.name
                countryMovie.text = movie.country
                dateMovie.text = movie.year
                ratingMovie.text = movie.ratingIMB
                descriptionMovie.text = movie.description
                var imgUrl = movie.poster
                Glide
                    .with(nameMovie)
                    .load(imgUrl)
                    .centerCrop()
                    .placeholder(R.drawable.progress_bar)
                    .into(previewImage)

                itemViewLayout.setOnClickListener {
                    onClickListenerMovie.onCLick(movie)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movies: List<MoviesData>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item, onClickListenerMovie)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MoviesData>() {
            override fun areItemsTheSame(oldItem: MoviesData, newItem: MoviesData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesData, newItem: MoviesData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}