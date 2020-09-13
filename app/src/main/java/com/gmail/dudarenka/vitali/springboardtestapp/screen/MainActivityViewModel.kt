package com.gmail.dudarenka.vitali.springboardtestapp.screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gmail.dudarenka.vitali.springboardtestapp.base.SingleLiveEvent
import com.gmail.dudarenka.vitali.springboardtestapp.entity.IconEntity
import com.gmail.dudarenka.vitali.springboardtestapp.entity.PageEntity
import com.gmail.dudarenka.vitali.springboardtestapp.screen.adapter.PageAdapter
import com.gmail.dudarenka.vitali.springboardtestapp.screen.adapter.payloads.NewIconPayLoad

private const val IMAGE_URL = "http://loremflickr.com/200/200/"

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var pageList: ArrayList<PageEntity> = ArrayList()
    var adapter: PageAdapter = PageAdapter()
    val pagesLoaded = SingleLiveEvent<PageAdapter>()

    init {
        initDefaultIconLists()
        pagesLoaded.postValue(adapter)
    }

    private fun initDefaultIconLists(){
        for (i in 0..1) {
            val iconList: ArrayList<IconEntity> = ArrayList()
            for (i in 0..69) {
                iconList.add(IconEntity(System.currentTimeMillis(), IMAGE_URL))
            }
            pageList.add(PageEntity(i.toLong(), iconList))
        }
        adapter.listData = pageList
    }

    fun onAddButtonClicked(position: Int) {
        val newIconEntity = IconEntity(System.currentTimeMillis(), IMAGE_URL)
        pageList[position].iconList.add(newIconEntity)
        adapter.notifyItemChanged(position, NewIconPayLoad(newIconEntity))
    }

    fun onReloadButtonClicked() {
        pageList.clear()
        initDefaultIconLists()
        adapter.notifyDataSetChanged()
    }

}