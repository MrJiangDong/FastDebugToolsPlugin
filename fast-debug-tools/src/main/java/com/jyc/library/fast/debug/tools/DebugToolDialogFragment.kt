package com.jyc.library.fast.debug.tools

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Method


/// @author jyc
/// 创建日期：2021/8/21
/// 描述：DebugToolDialogFragment
class DebugToolDialogFragment : AppCompatDialogFragment() {
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val parent = dialog?.window?.findViewById(android.R.id.content) ?: container
        val view = inflater.inflate(R.layout.fast_debug_tool, parent, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        dialog?.window?.setLayout(
            (getDisplayWidthInPx(view?.context) * 0.7f).toInt(),
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(R.drawable.shape_fast_debug_tool)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDecoration = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(
            ContextCompat.getDrawable(
                view.context,
                R.drawable.shape_fast_debug_tool_divider
            )!!
        )

        //去解析定义好的功能
        val functions = DebugToolsParse().parse()
        recyclerView?.addItemDecoration(itemDecoration)
        recyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = DebugToolAdapter(functions)
    }

    inner class DebugToolAdapter(val list: List<DebugFunction>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView = layoutInflater.inflate(R.layout.fast_debug_tool_item, parent, false)
            return object : RecyclerView.ViewHolder(itemView) {

            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val debugFunction = list[position]
            val itemTitle = holder.itemView.findViewById<TextView>(R.id.item_title)
            val itemDesc = holder.itemView.findViewById<TextView>(R.id.item_desc)

            itemTitle.text = debugFunction.name
            if (TextUtils.isEmpty(debugFunction.desc)) {
                itemDesc.visibility = View.GONE
            } else {
                itemDesc.visibility = View.VISIBLE
                itemDesc.text = debugFunction.desc
            }

            if (debugFunction.enable) {
                holder.itemView.setOnClickListener {
                    debugFunction.invoke()
                }
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }

    private fun getDisplayWidthInPx(context: Context?): Int {
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }
}