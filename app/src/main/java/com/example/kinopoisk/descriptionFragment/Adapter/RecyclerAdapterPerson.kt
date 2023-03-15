package com.example.kinopoisk.descriptionFragment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.ItemPersonBinding
import com.example.kinopoisk.descriptionFragment.models.modelPersons.Person


class RecyclerAdapterPerson : RecyclerView.Adapter<RecyclerAdapterPerson.PersonViewHolder>() {

    inner class PersonViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var cites: List<Person>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun getItemCount(): Int = cites.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterPerson.PersonViewHolder {
        return PersonViewHolder(ItemPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.apply {
            val person = cites[position]
            namePerson.text = person.name
            val idImg = person.photo
            Glide
                .with(namePerson)
                .load(idImg)
                .centerCrop()
                .placeholder(R.drawable.progress_bar)
                .into(imagePerson)
        }
    }
}