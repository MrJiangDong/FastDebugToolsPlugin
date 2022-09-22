package com.jyc.library.fast.debug.tools

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Process

/// @author jyc
/// 创建日期：2021/8/21
/// 描述：DebugTools
class DebugToolsLogic() : DebugToolsFunInterface{

    @FastToolsFunction
    override fun buildVersion(): String {
        //构建版本 : 1.0.1
        return "打包版本:" + DebugToolsManager.debugToolsConfig?.versionCode + "-" +  DebugToolsManager.debugToolsConfig?.versionName
    }

    @FastToolsFunction
    override fun buildTime(): String {
        //new date() 当前运行时拿到的时间，这个包 被打出来的时间
        return "打包时间:" + BuildConfig.BUILD_TIME
    }

    @FastToolsFunction
    override fun buildEnvironment(): String {
        return "打包环境: ${
            when (BuildConfig.DEBUG) {
                true -> {
                    "Debug环境"
                }

                false -> {
                    "Release环境"
                }
            }
        }"
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Dev环境")
    override fun switchServerToDev() {
        DebugToolsSPUtil.putString(DebugToolsManager.BASE_URL_TYPE_KEY,DebugToolsManager.BASE_DEV_KEY)
        killProcessAndLaunchMain()
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Test环境")
    override fun switchServerToTest() {
        DebugToolsSPUtil.putString(DebugToolsManager.BASE_URL_TYPE_KEY, DebugToolsManager.BASE_TEST_KEY)
        killProcessAndLaunchMain()
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Demo环境")
    override fun switchServerToDemo() {
        DebugToolsSPUtil.putString(DebugToolsManager.BASE_URL_TYPE_KEY, DebugToolsManager.BASE_DEMO_KEY)
        killProcessAndLaunchMain()
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Api环境")
    override fun switchServerToApi() {
        DebugToolsSPUtil.putString(DebugToolsManager.BASE_URL_TYPE_KEY, DebugToolsManager.BASE_API_KEY)
        killProcessAndLaunchMain()
    }

    private fun killProcessAndLaunchMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            val context = AppGlobals.get()?.applicationContext ?: return@postDelayed
            //得到启动页的intent
            val intent =
                context.packageManager.getLaunchIntentForPackage(context.packageName)
            intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

            //杀掉当前进程,并主动启动新的 启动页，以完成重启的动作
            Process.killProcess(Process.myPid())
        }, 200)
    }
}