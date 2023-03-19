package com.example.kinopoisk.descriptionFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.R
import com.example.kinopoisk.descriptionFragment.api.RetrofitClientDescription
import com.example.kinopoisk.descriptionFragment.models.modelForDescription.ResponsesDescription
import com.example.kinopoisk.descriptionFragment.models.modelPersons.Person
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DescriptionFragmentViewModel : ViewModel() {
    private var _movie = MutableSharedFlow<ResponsesDescription?>()
    val movie = _movie.asSharedFlow()

     private var stateFragmentDescription =
        Pair<Int, Int>(R.id.action_descriptionMovieFragment2_to_mainFragment, 666)
    val flowState = flow<Pair<Int, Int>> { emit(stateFragmentDescription) }
    fun stateFragmentDescription(dest: Int, id: Int) {
        stateFragmentDescription = Pair(dest,id)
    }

    fun getMovie(id: Int) {
        viewModelScope.launch() {
            try {
                val response =
                    RetrofitClientDescription.apiDescriptionMovie.getMovie(id).body()
                _movie.emit(response)
            } catch (e: IOException) {
                println("onCreate: not internet")
            } catch (e: HttpException) {
                println("HttpException")
            }
        }
    }

    private var _persons = MutableSharedFlow<List<Person>>()
    val persons = _persons.asSharedFlow()

    fun getPersons(id: Int) {
        viewModelScope.launch() {
            try {
                val response =
                    RetrofitClientDescription.apiPersonsMovie.getPersons(id)
                        .body()?.docs?.get(0)?.persons
                _persons.emit(response ?: arrayListOf())
            } catch (e: IOException) {
                println("onCreate: not internet")
            } catch (e: HttpException) {
                println("HttpException")
            }
        }
    }
}