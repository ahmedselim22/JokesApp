package com.selim.jokesapp.model

import android.util.Log
import com.google.gson.Gson
import com.selim.jokesapp.model.domain.Joke
import com.selim.jokesapp.model.domain.SeveralJokes
import okhttp3.*
import java.io.IOException

object DataManager {
    private val jokesList: MutableList<Joke> = mutableListOf()
    private val client = OkHttpClient()
    private const val TAG = "ahmed"
    private val url = HttpUrl.Builder()
        .scheme("https")
        .host("v2.jokeapi.dev")
        .addPathSegment("joke")
        .addPathSegment("Any")
        .addQueryParameter("amount", "10")
        .build()

    val jokes: List<Joke>
        get() = jokesList.toList()

    fun getJokes(){
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonResponse ->
                    val result = Gson().fromJson(jsonResponse, SeveralJokes::class.java)
                    for (i in 0 until result.amount) {
                        val currentJoke = result.jokes[i]
                        if (currentJoke.delivery == null || currentJoke.setup == null) {
                            continue
                        }
                        if (jokesList.contains(currentJoke)){
                            continue
                        }
                        val joke = Joke(
                            currentJoke.error,
                            currentJoke.category,
                            currentJoke.type,
                            currentJoke.setup,
                            currentJoke.delivery,
                            currentJoke.flags,
                            currentJoke.id,
                            currentJoke.safe,
                            currentJoke.lang
                        )
                        Log.d(TAG, "joke $i $joke \n")
                        jokesList.add(joke)
                    }
                    Log.d(TAG, jokesList.size.toString())
                }

            }

        })
    }

}