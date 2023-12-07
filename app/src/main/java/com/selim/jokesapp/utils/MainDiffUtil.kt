package com.selim.jokesapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.selim.jokesapp.model.domain.Joke

class MainDiffUtil(private val oldList: List<Joke>,private val newList: List<Joke>) :DiffUtil.Callback() {
    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList[oldItemPosition].setup.equals(newList[newItemPosition].setup)){
            return true
        }
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

}