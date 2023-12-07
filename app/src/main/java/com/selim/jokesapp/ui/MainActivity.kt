package com.selim.jokesapp.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.selim.jokesapp.adapters.JokesAdapter
import com.selim.jokesapp.databinding.ActivityMainBinding
import com.selim.jokesapp.model.DataManager
import com.selim.jokesapp.model.domain.Joke
import com.selim.jokesapp.viewModels.JokeViewModel

class MainActivity : AppCompatActivity(), OnItemClicked {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }
        setup()
    }

    private fun setup() {
        val viewModel: JokeViewModel = ViewModelProvider(this).get(JokeViewModel::class.java)
        viewModel.getJokes()
        Log.d("ahmed", DataManager.jokes.size.toString())
        val adapter = JokesAdapter(DataManager.jokes, this)
        binding.mainRecyclerView.adapter = adapter
        binding.mainBtnLoad.setOnClickListener {
            viewModel.getJokes()
            viewModel.jokes.observe(this, Observer { jokes ->
                adapter.setData(jokes)
                adapter.notifyDataSetChanged()
            })
        }
    }

    private fun copyTextToClipboard(joke: Joke) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("joke", joke.setup + " " + joke.delivery)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "copied", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(joke: Joke) {
        copyTextToClipboard(joke)
    }


}