package com.jyc.library.fast.debug.tools

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Process

/// @author jyc
/// 创建日期：2021/8/21
/// 描述：DebugTools
class DebugTools : DebugToolsFunInterface{
    private val debugToolsLogic : DebugToolsLogic = DebugToolsLogic()

    @FastToolsFunction
    override fun buildVersion(): String {
        //构建版本 : 1.0.1
        return debugToolsLogic.buildVersion()
    }

    @FastToolsFunction
    override fun buildTime(): String {
        //new date() 当前运行时拿到的时间，这个包 被打出来的时间
        return debugToolsLogic.buildTime()
    }

    @FastToolsFunction
    override fun buildEnvironment(): String {
        return debugToolsLogic.buildEnvironment()
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Dev环境")
    override fun switchServerToDev() {
        debugToolsLogic.switchServerToDev()
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Test环境")
    override fun switchServerToTest() {
        debugToolsLogic.switchServerToTest()
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Demo环境")
    override fun switchServerToDemo() {
        debugToolsLogic.switchServerToDemo()
    }

    @FastToolsFunction
    @FastDebug(name = "环境切换", desc = "切换为Api环境")
    override fun switchServerToApi() {
        debugToolsLogic.switchServerToApi()
    }
}