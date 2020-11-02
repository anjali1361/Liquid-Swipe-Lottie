package com.anjali.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anjali.myapplication.*
import com.anjali.myapplication.DynamiRVInterface.LoadMore
import com.anjali.myapplication.adapter.DyanamicRvAdapter
import com.anjali.myapplication.adapter.StaticRecyclerViewAdapter
import com.anjali.myapplication.models.DynamicRVModelClass
import com.anjali.myapplication.models.StaticRecyclerViewModel
import java.util.*
import kotlin.collections.ArrayList

class DashboardActivity : AppCompatActivity() {

    lateinit var rv1: RecyclerView
    lateinit var drv:RecyclerView
    val item = ArrayList<StaticRecyclerViewModel>()
    lateinit var staticRecyclerViewAdapter: StaticRecyclerViewAdapter

    val items = ArrayList<DynamicRVModelClass>()
    lateinit var dynamicRecyclerViewAdapter: DyanamicRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //adding items inside arrayList with the help of StaticRecyclerViewModel
        item.add(StaticRecyclerViewModel(R.drawable.img2,"Alumni"))
        item.add(StaticRecyclerViewModel(R.drawable.img3,"Teachers"))
        item.add(StaticRecyclerViewModel(R.drawable.img1,"Non-Teachers"))
        item.add(StaticRecyclerViewModel(R.drawable.img2,"Student"))
        item.add(StaticRecyclerViewModel(R.drawable.img3,"Security"))
        item.add(StaticRecyclerViewModel(R.drawable.img1,"Mess Service"))
        item.add(StaticRecyclerViewModel(R.drawable.img2,"First Aid"))



        rv1 = findViewById(R.id.rv_1)
        staticRecyclerViewAdapter = StaticRecyclerViewAdapter((item))
        rv1.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv1.adapter = staticRecyclerViewAdapter

        items.add(DynamicRVModelClass("Newsfeed"))
        items.add(DynamicRVModelClass("Market Place"))
        items.add(DynamicRVModelClass("Admin Block"))
        items.add(DynamicRVModelClass("Q&A"))
        items.add(DynamicRVModelClass("Notifications"))
        items.add(DynamicRVModelClass("Google Map"))
        items.add(DynamicRVModelClass("All In One"))
        items.add(DynamicRVModelClass("GH"))
        items.add(DynamicRVModelClass("OP"))
        items.add(DynamicRVModelClass("MG"))
        items.add(DynamicRVModelClass("Lecture Hall"))

        drv = findViewById(R.id.rv_2)
        drv.layoutManager = LinearLayoutManager(this)
        dynamicRecyclerViewAdapter = DyanamicRvAdapter(drv,this,items)
        drv.adapter= dynamicRecyclerViewAdapter

        dynamicRecyclerViewAdapter.setLoadMore(object : LoadMore {
            override fun onLoadMore() {
                if (items.size <= 5) {
                    items.add(DynamicRVModelClass(""))
                    dynamicRecyclerViewAdapter.notifyItemInserted((items.size - 1))
                    Handler().postDelayed(Runnable {
                        run {
                            items.removeAt(items.size - 1)
                            dynamicRecyclerViewAdapter.notifyItemRemoved(items.size)

                            val index = items.size
                            val end = index + 10

                            for (i in 0..end) {
                                val name = UUID.randomUUID().toString()
                                val item = DynamicRVModelClass(name)
                                items.add(item)
                            }
                            dynamicRecyclerViewAdapter.notifyDataSetChanged()
                            dynamicRecyclerViewAdapter.setLoaded()
                        }
                    }, 4000)

                }
                else{
                   Toast.makeText(this@DashboardActivity, "Data Completed", Toast.LENGTH_SHORT).show()

                }
                super.onLoadMore()
            }
        })

    }

}