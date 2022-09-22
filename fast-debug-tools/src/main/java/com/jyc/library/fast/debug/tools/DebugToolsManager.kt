package com.jyc.library.fast.debug.tools

object DebugToolsManager {
//    var VERSION_CODE
    internal const val BASE_URL_TYPE_KEY = "BASE_URL_TYPE_KEY"
    internal const val BASE_DEV_KEY = "DEV"
    internal const val BASE_TEST_KEY = "TEST"
    internal const val BASE_DEMO_KEY = "DEMO"
    internal const val BASE_API_KEY = "API"
    var debugToolsConfig : DebugToolsConfig? = null

    fun debugToolsInit(versionName: String,versionCode: Int){
       debugToolsConfig = DebugToolsConfig(versionName,versionCode)
    }

    fun getCacheUrl():String{
        return DebugToolsSPUtil.getString(BASE_URL_TYPE_KEY)?:""
    }

    data class DebugToolsConfig(
        var versionName:String,
        var versionCode:Int,
    )
}