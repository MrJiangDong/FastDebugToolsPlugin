package com.jyc.library.fast.debug.tools

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Process

/// @author jyc
/// 创建日期：2021/8/21
/// 描述：DebugTools
class DebugToolsChild : DebugTools(){
    private val debugToolsLogic : DebugToolsLogic = DebugToolsLogic()

    @FastDebugEnable(order = 1)
    override fun buildVersion(): String {
        //构建版本 : 1.0.1
        return debugToolsLogic.buildVersion()
    }

    @FastDebugEnable(order = 2)
    override fun buildTime(): String {
        //new date() 当前运行时拿到的时间，这个包 被打出来的时间
        return debugToolsLogic.buildTime()
    }

    @FastDebugEnable(order = 3)
    override fun buildEnvironment(): String {
        return debugToolsLogic.buildEnvironment()
    }

    @FastDebugEnable(order = 4)
    @FastDebug(name = "环境切换", desc = "切换为Dev环境")
    override fun switchServerToDev() {
        debugToolsLogic.switchServerToDev()
    }

    @FastDebugEnable(order = 5)
    @FastDebug(name = "环境切换", desc = "切换为Test环境")
    override fun switchServerToTest() {
        debugToolsLogic.switchServerToTest()
    }

    @FastDebugEnable(order = 6)
    @FastDebug(name = "环境切换", desc = "切换为Demo环境")
    override fun switchServerToDemo() {
        debugToolsLogic.switchServerToDemo()
    }

    @FastDebugEnable(order = 7)
    @FastDebug(name = "环境切换", desc = "切换为Api环境")
    override fun switchServerToApi() {
        debugToolsLogic.switchServerToApi()
    }
}