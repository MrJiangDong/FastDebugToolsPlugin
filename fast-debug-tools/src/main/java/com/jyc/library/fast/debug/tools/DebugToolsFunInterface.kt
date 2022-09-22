package com.jyc.library.fast.debug.tools

interface DebugToolsFunInterface {
    fun buildVersion() : String

    fun buildTime():String

    fun buildEnvironment():String

    fun switchServerToDev()

    fun switchServerToTest()

    fun switchServerToDemo()

    fun switchServerToApi()
}