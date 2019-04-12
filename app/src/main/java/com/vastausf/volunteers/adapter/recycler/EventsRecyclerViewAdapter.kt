package com.vastausf.volunteers.adapter.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.R
import com.vastausf.volunteers.model.volunteers.data.EventDataFull
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat
import java.util.Locale

class EventsRecyclerViewAdapter(
    private val picasso: Picasso,
    private val items: List<EventDataFull>,
    private val onItemClick: (Long) -> Unit,
    private val onLikeClick: (Long, Boolean) -> Unit,
    private val onJoinClick: (Long, Boolean) -> Unit,
    private val onLinkClick: (String) -> Unit,
    private val onCreateLastElement: (Int) -> Unit
) : RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(view.context).inflate(R.layout.item_event, view, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items.size - 1 == position)
            onCreateLastElement(items.size)

        holder.bind(position)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var itemData: EventDataFull

        fun bind(itemPosition: Int) {
            itemData = items[itemPosition]

            println(itemData)

            itemView.apply {
                setOnClickListener { onItemClick(itemData.id) }
                tvItemEventTitle.text = itemData.title
                tbItemEventLike.isChecked = itemData.liked
                tbItemEventJoin.isChecked = itemData.joined
                tvItemEventLikeCounter.text = itemData.likeCount.toString()
                tvItemEventJoinCounter.text = itemData.joinCount.toString()

                itemData.place?.let {
                    tvItemEventPlace.apply {
                        visibility = View.VISIBLE
                        text = it
                    }
                }
                itemData.datetime?.let {
                    tvItemEventDateTime.apply {
                        visibility = View.VISIBLE
                        val dateTimeString =
                            SimpleDateFormat("H:mm dd MMMM yyyy", Locale.getDefault()).format(
                                itemData.datetime)

                        text = if (itemData.duration != null)
                            "$dateTimeString (${SimpleDateFormat("H:mm",
                                Locale.getDefault()).format(itemData.duration)})"
                        else
                            dateTimeString
                    }
                }
                itemData.description?.let { description ->
                    tvItemEventDescription.text = description
                }
                itemData.link?.let { link ->
                    ibItemEventLink.visibility = View.VISIBLE
                    ibItemEventLink.setOnClickListener {
                        onLinkClick(link)
                    }
                }
                itemData.image?.let { image ->
                    ivItemEventImage.visibility = View.VISIBLE

                    picasso
                        .load(image)
                        .placeholder(R.drawable.placeholder_image)
                        .into(ivItemEventImage)
                }

                tbItemEventLike.setOnClickListener {
                    it as ToggleButton
                    tvItemEventLikeCounter.apply {
                        text = if (it.isChecked)
                            (text.toString().toInt() + 1).toString()
                        else
                            (text.toString().toInt() - 1).toString()
                    }
                    onLikeClick(itemData.id, it.isChecked)
                }
                tbItemEventJoin.setOnClickListener {
                    it as ToggleButton
                    tvItemEventJoinCounter.apply {
                        text = if (it.isChecked)
                            (text.toString().toInt() + 1).toString()
                        else
                            (text.toString().toInt() - 1).toString()
                    }
                    onJoinClick(itemData.id, it.isChecked)
                }
            }
        }
    }

}
