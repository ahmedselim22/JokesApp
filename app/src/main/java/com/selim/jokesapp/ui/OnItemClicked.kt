package com.selim.jokesapp.ui

import com.selim.jokesapp.model.domain.Joke

interface OnItemClicked {
    fun onItemClick(joke:Joke)
}