package com.example.kinopoisk.mainFragment.innerFragment.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.ItemMovieBinding
import com.example.kinopoisk.mainFragment.model.recyclerLoad.Movies


class RecyclerAdapterMovie(private val onClickListenerMovie: onClickListenerMovie) :
    PagingDataAdapter<Movies, RecyclerAdapterMovie.MovieViewHolder>(
        diffCallback) {

    class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies?, onClickListenerMovie: onClickListenerMovie) {
            with(binding) {
                nameMovie.text = movie?.name
                val countries = movie?.countries
                countryMovie.text = if(countries?.isNotEmpty() == true) countries.get(0).name.filter { !it.isWhitespace()} else "Not country".filter { !it.isWhitespace()}
                dateMovie.text = movie?.year.toString()
                ratingMovie.text = movie?.rating?.imdb.toString()
                descriptionMovie.text = movie?.description
                var imgUrl = movie?.poster?.previewUrl
                if(imgUrl == null) imgUrl = movie?.poster?.url
                Glide
                    .with(nameMovie)
                    .load(imgUrl)
                    .centerCrop()
                    .placeholder(R.drawable.progress_bar)
                    .into(previewImage)

                itemViewLayout.setOnClickListener {
                    onClickListenerMovie.onCLick(movie!!.id)
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

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListenerMovie)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }
        }
    }
}