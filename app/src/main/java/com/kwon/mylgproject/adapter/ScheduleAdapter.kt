package com.kwon.mylgproject.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kwon.mylgproject.R
import com.kwon.mylgproject.data.ScheduleRecord
import kotlinx.android.synthetic.main.list_item_schedule.view.*

class ScheduleAdapter : PagingDataAdapter<ScheduleRecord, ScheduleAdapter.ScheduleViewHolder>(Diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_schedule, parent, false)
        )
    }

    companion object {
        var temp_id: Int? = null

        private val Diff = object : DiffUtil.ItemCallback<ScheduleRecord>() {
            override fun areItemsTheSame(
                oldItem: ScheduleRecord,
                newItem: ScheduleRecord
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ScheduleRecord,
                newItem: ScheduleRecord
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        getItem(position).let {
            Log.d("TEST", "Item -> $it")
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val sc_title = view.sc_title
        private val sc_content = view.sc_content
        private val sc_memo = view.sc_memo
        private lateinit var item: ScheduleRecord

        fun bind(item: ScheduleRecord) {
            this.item = item
            sc_title.text = item.title
            sc_content.text = item.contents
            sc_memo.text = item.memo
        }
    }
}