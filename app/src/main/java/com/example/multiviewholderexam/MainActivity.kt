package com.example.multiviewholderexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var testAdapter : TestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = ArrayList<TestData>()

        for(i in 0 until 5) {
            items.add(TestData(1,i.toString()))
        }
        items.add(TestData(2, "더보기"))

        testAdapter = TestAdapter(this@MainActivity, items)
        testAdapter.itemClickListener(itemClickListener)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView?.adapter = testAdapter

        val testLayoutManger = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = testLayoutManger
    }
    private val itemClickListener = object : ItemClickListener {
        override fun onClick(item: String) {
            Log.e("tag", "item click "+ item)
        }
    }
}