package com.vastausf.volunteers.adapter.events

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vastausf.volunteers.R
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat
import java.util.Locale

class EventsRecyclerViewAdapter(
    private val items: List<EventsRecyclerViewItem>,
    private val onLikeClick: (Long) -> Unit,
    private val onJoinClick: (Long) -> Unit,
    private val onLinkClick: (String) -> Unit
): RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(view.context).inflate(R.layout.item_event, view, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(itemData: EventsRecyclerViewItem) {
            view.apply {
                tvItemEventTitle.text = itemData.title
                tvItemEventPlace.text = itemData.place
                tvItemEventDateTime.text = "${SimpleDateFormat("H:mm dd MMMM yyyy", Locale.getDefault()).format(itemData.datetime)} (${SimpleDateFormat("H:mm", Locale.getDefault()).format(itemData.duration)})"
                tvItemEventDescription.text = itemData.description
                tvItemEventLikeCounter.text = itemData.likeCount.toString()
                tvItemEventJoinCounter.text = itemData.joinCount.toString()

                if (itemData.liked)
                    ibItemEventLike.setImageDrawable(context.getDrawable(R.drawable.ic_like))
                else
                    ibItemEventLike.setImageDrawable(context.getDrawable(R.drawable.ic_like_outline))

                if (itemData.joined)
                    ibItemEventJoin.setImageDrawable(context.getDrawable(R.drawable.ic_join))
                else
                    ibItemEventJoin.setImageDrawable(context.getDrawable(R.drawable.ic_join_outline))

                ibItemEventLike.setOnClickListener {
                    onLikeClick(itemData.id)
                }
                ibItemEventJoin.setOnClickListener {
                    onJoinClick(itemData.id)
                }
                ibItemEventLink.setOnClickListener {
                    onLinkClick(itemData.link)
                }
            }
        }
    }

}