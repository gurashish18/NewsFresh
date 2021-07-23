package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class NewsListAdapter( private val listener : NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items : ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // Layoutinflater convert XML into view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.authorView.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.urlToimage).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val imageView: ImageView = itemView.findViewById(R.id.image)
    val authorView : TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked{
    fun onItemClicked(item : News)
}