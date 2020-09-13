package com.gmail.dudarenka.vitali.springboardtestapp.screen.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.dudarenka.vitali.springboardtestapp.R
import com.gmail.dudarenka.vitali.springboardtestapp.dpToPx
import com.gmail.dudarenka.vitali.springboardtestapp.entity.PageEntity
import com.gmail.dudarenka.vitali.springboardtestapp.screen.adapter.payloads.NewIconPayLoad

class PageAdapter : RecyclerView.Adapter<PageAdapter.Holder>() {

    private var itemsList: ArrayList<PageEntity> = ArrayList()
    private var context: Context? = null

    var listData: MutableList<PageEntity>? = ArrayList()
        set(value) {
            field = value
            itemsList.clear()
            itemsList.addAll(this.listData!!)
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PageAdapter.Holder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.page_item, viewGroup, false)
        context = viewGroup.context
        return Holder(view)
    }

    override fun getItemCount(): Int = listData!!.size

    override fun onBindViewHolder(holder: PageAdapter.Holder, position: Int) {
        val iconAdapter = IconAdapter()
        iconAdapter.listData = itemsList[position].iconList
        holder.iconRecycler.adapter = iconAdapter
        val layoutManager = GridLayoutManager(context, 7)
        holder.iconRecycler.layoutManager = layoutManager

        holder.iconRecycler.addItemDecoration(
            GridSpacingItemDecoration(
                7,
                dpToPx(2f, context!!),
                true
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            if (payloads[0] is NewIconPayLoad) {
                (holder.iconRecycler.adapter as IconAdapter).addItem((payloads[0] as NewIconPayLoad).newIcon)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var iconRecycler: RecyclerView = view.findViewById(R.id.icons_recycler)

    }

}