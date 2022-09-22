package com.jyc.fast.debug.tools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jyc.library.fast.debug.tools.DebugToolsManager
import java.lang.reflect.InvocationTargetException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DebugToolsManager.debugToolsInit(BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE)

        try {
            val aClass = Class.forName("com.jyc.library.fast.debug.tools.DebugToolDialogFragment")
            val target = aClass.getConstructor().newInstance() as DialogFragment
            target.show(this.getSupportFragmentManager(), "debug_tool")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}