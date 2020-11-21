package com.example.multiviewholderexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class TestAdapter (
    private val context : Context,
    private val listItems : List<TestData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener : ItemClickListener

    fun itemClickListener(listener : ItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            1 -> {
                ItemViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_test, parent, false)
                )
            }
            2 -> {
                MoreItemViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_more, parent, false)
                )
            }
            else -> throw RuntimeException ("에러")
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return listItems[position].type
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = listItems[position]

        when(obj.type) {
            1 -> (holder as ItemViewHolder).bind()
            2 -> (holder as MoreItemViewHolder).bind()
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = listItems[adapterPosition]

            val testText : TextView = itemView.findViewById(R.id.test_text)
            testText.text = item.data

            itemView.setOnClickListener {
                listener.onClick(item.data)
            }
        }

    }
    inner class MoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = listItems[adapterPosition]
            itemView.setOnClickListener {
                listener.onClick(item.data)
            }
        }
    }
}