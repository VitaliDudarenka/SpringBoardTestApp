package com.gmail.dudarenka.vitali.springboardtestapp.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gmail.dudarenka.vitali.springboardtestapp.R
import com.gmail.dudarenka.vitali.springboardtestapp.dpToPx
import com.gmail.dudarenka.vitali.springboardtestapp.entity.IconEntity
import java.util.*


class IconAdapter : RecyclerView.Adapter<IconAdapter.Holder>() {

    private var itemsList: ArrayList<IconEntity> = ArrayList()
    private var context: Context? = null

    var listData: MutableList<IconEntity>? = ArrayList()
        set(value) {
            field = value
            itemsList.clear()
            itemsList.addAll(this.listData!!)
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): IconAdapter.Holder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.icon_item, viewGroup, false)
        context = viewGroup.context
        return Holder(view)
    }

    override fun getItemCount(): Int = listData!!.size

    override fun onBindViewHolder(holder: IconAdapter.Holder, position: Int) {
        val requestOptions = RequestOptions()
        requestOptions.transform(FitCenter(), RoundedCorners(dpToPx(7f, context!!)))
        Glide
            .with(context!!)
            .load(itemsList[position].imageUrl + "?=" + System.currentTimeMillis())
            .apply(requestOptions)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.iconImageView)

    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var iconImageView: ImageView = view.findViewById(R.id.icon_iv)

    }

    fun addItem(iconEntity: IconEntity) {
        itemsList.add(iconEntity)
        notifyItemInserted(itemsList.size - 1)
    }

}