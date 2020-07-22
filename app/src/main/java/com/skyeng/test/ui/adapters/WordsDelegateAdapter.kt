package com.skyeng.test.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyeng.test.R
import com.skyeng.test.entities.WordEntity
import com.skyeng.test.ui.list.DelegateAdapter
import com.skyeng.test.ui.list.OnItemClickedListener
import com.skyeng.test.ui.list.ViewType
import kotlinx.android.synthetic.main.item_word.view.*

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
class WordsDelegateAdapter(private val listener: OnItemClickedListener<WordEntity>) :
    DelegateAdapter<ViewType> {

    private class WordViewHolder(rootView: View, private val listener: OnItemClickedListener<WordEntity>) :
        RecyclerView.ViewHolder(rootView) {

        fun bind(word: WordEntity) = with(itemView) {
            textWord.text = word.text
            itemView.setOnClickListener {
                listener.onItemClicked(word, it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder = WordViewHolder(
        LayoutInflater.from(parent?.context).inflate(
            R.layout.item_word, parent, false
        ), listener
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: ViewType) {
        (holder as WordViewHolder).bind(item as WordEntity)
    }

    override fun update(item: ViewType) {
    }
}