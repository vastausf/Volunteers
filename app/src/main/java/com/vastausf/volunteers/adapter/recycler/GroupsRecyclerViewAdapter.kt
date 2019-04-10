package com.vastausf.volunteers.adapter.recycler

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.R
import com.vastausf.volunteers.model.volunteers.data.EventDataFull
import com.vastausf.volunteers.model.volunteers.data.GroupDataFull
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_event.view.*
import kotlinx.android.synthetic.main.item_group.view.*
import java.text.SimpleDateFormat
import java.util.Locale

class GroupsRecyclerViewAdapter(
    private val picasso: Picasso,
    private val items: List<GroupDataFull>,
    private val onItemClick: (Long) -> Unit,
    private val onCreateLastElement: (Int) -> Unit
): RecyclerView.Adapter<GroupsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(view.context).inflate(R.layout.item_group, view, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items.size - 1 == position)
            onCreateLastElement(items.size)

        holder.bind(items[position])
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(itemData: GroupDataFull) {
            itemView.apply {
                setOnClickListener { onItemClick(itemData.id) }
                tvItemGroupTitle.text = itemData.title
                picasso
                    .load(itemData.image)
                    .placeholder(R.drawable.placeholder_image_round)
                    .transform(RoundedCornersTransformation(-1, 0))
                    .into(ivItemGroupIcon)

                if (itemData.joined)
                    llItemGroupRoot.setBackgroundResource(R.drawable.background_group_joined)
            }
        }
    }

}