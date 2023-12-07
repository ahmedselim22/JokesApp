package com.selim.jokesapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selim.jokesapp.model.DataManager
import com.selim.jokesapp.model.domain.Joke

class JokeViewModel:ViewModel() {
    val jokes =MutableLiveData<List<Joke>>()
    fun getJokes(){
        DataManager.makeRequest()
        jokes.postValue(DataManager.jokes)
    }
}