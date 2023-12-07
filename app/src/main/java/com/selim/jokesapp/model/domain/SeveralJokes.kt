package com.selim.jokesapp.model.domain
data class SeveralJokes(
    val error:Boolean,
    val amount:Int,
    val jokes:List<Joke>
)
