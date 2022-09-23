package com.jyc.fast.debug.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.jyc.library.fast.debug.tools.DebugToolsManager
import java.lang.reflect.InvocationTargetException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DebugToolsManager.debugToolsInit(BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE)

        val textView = findViewById<TextView>(R.id.tv_click)
        textView.setOnClickListener {
            DebugToolsManager.showDebugToolsDialog(this)
        }
    }
}