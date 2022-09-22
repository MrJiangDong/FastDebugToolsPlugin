package com.jyc.library.fast.debug.tools

import android.content.Context
import android.content.SharedPreferences


/// @author jyc
/// 创建日期：2021/8/11
/// 描述：SPUtil
internal object DebugToolsSPUtil {

    private const val CACHE_FILE = "BMC"

    fun putString(key: String, value: String) {
        val shared = getShared()
        shared?.edit()?.putString(key, value)?.apply()
    }

    fun getString(key: String): String? {
        val shared = getShared()
        if (shared != null) {
            return shared.getString(key, null)
        }
        return null
    }

    fun putBoolean(key: String, value: Boolean) {
        val shared = getShared()
        shared?.edit()?.putBoolean(key, value)?.apply()
    }


    fun getBoolean(key: String): Boolean {
        val shared = getShared()
        if (shared != null) {
            return shared.getBoolean(key, false)
        }
        return false
    }

    fun putInt(key: String, value: Int) {
        val shared = getShared()
        shared?.edit()?.putInt(key, value)?.apply()
    }


    fun getInt(key: String): Int {
        val shared = getShared()
        if (shared != null) {
            return shared.getInt(key, 0)
        }
        return 0
    }

    private fun getShared(): SharedPreferences? {
        val application = AppGlobals.get()
        if (application != null) {
            return application.getSharedPreferences(CACHE_FILE, Context.MODE_PRIVATE)
        }
        return null
    }
}