package com.geeui.guide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.geeui.guide.databinding.ActivityMainBinding
import com.geeui.guide.databinding.ItemListBinding
import com.geeui.guide.model.ListItemModel

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.included.back.setOnClickListener { finish() }
        initRecyclerview()
    }

    private fun initRecyclerview() {
        val adapter = TestAdapter()
        val list = arrayListOf(
            ListItemModel(
                "中文", icon = 0, action = DetailActivity::class.java, argument = "zh"
            ), ListItemModel("English", 0, action = DetailActivity::class.java, argument = "en")
        )
        adapter.submitList(list)
        adapter.setOnItemClickListener { adapter, view, position ->
            var data = list.get(position)
            when (data.type) {
                "activity" -> {
                    startActivity(Intent(this@MainActivity, data.action).apply {
                        putExtra("language", data.argument)
                    })
                }
                else -> {
                    var t = Toast.makeText(
                        this@MainActivity, "${data.desc}${data.type}", Toast.LENGTH_SHORT
                    )
                    t.setGravity(Gravity.CENTER, 0, 0)
                    t.show()
                }
            }
        }
        binding.recyclerview.adapter = adapter
    }

    class TestAdapter : BaseQuickAdapter<ListItemModel, TestAdapter.VH>() {

        // 自定义ViewHolder类
        class VH(
            parent: ViewGroup,
            val binding: ItemListBinding = ItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
            // 返回一个 ViewHolder
            return VH(parent)
        }

        override fun onBindViewHolder(holder: VH, position: Int, item: ListItemModel?) {
            // 设置item数据
            holder.binding.data = item
            holder.binding.icon.setImageResource(item?.icon ?: 0)
        }

    }
}