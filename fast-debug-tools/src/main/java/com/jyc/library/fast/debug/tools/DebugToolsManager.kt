package com.jyc.library.fast.debug.tools

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.lang.reflect.InvocationTargetException

object DebugToolsManager {
    //    var VERSION_CODE
    internal const val BASE_URL_TYPE_KEY = "BASE_URL_TYPE_KEY"
    internal const val BASE_DEV_KEY = "DEV"
    internal const val BASE_TEST_KEY = "TEST"
    internal const val BASE_DEMO_KEY = "DEMO"
    internal const val BASE_API_KEY = "API"
    var debugToolsConfig: DebugToolsConfig? = null
    private var debugTools: Array<Class<*>> = arrayOf(DebugTools::class.java)

    fun debugToolsInit(versionName: String, versionCode: Int) {
        debugToolsConfig = DebugToolsConfig(versionName, versionCode)
    }

    fun getCacheUrl(): String {
        return DebugToolsSPUtil.getString(BASE_URL_TYPE_KEY) ?: ""
    }

    fun showDebugToolsDialog(activity: AppCompatActivity) {
        try {
            val aClass = Class.forName("com.jyc.library.fast.debug.tools.DebugToolDialogFragment")
            val target = aClass.getConstructor().newInstance() as DialogFragment
            target.show(activity.supportFragmentManager, "debug_tool")
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

    fun setDebugToolsParseClass(array: Array<Class<*>>) {
        debugTools = array
    }

    fun getDebugToolsParasClass(): Array<Class<*>> {
        return debugTools
    }

    data class DebugToolsConfig(
        var versionName: String,
        var versionCode: Int,
    )
}