package com.jyc.library.fast.debug.tools

import java.lang.reflect.Method


/// @author jyc
/// 创建日期：2022/9/23
/// 描述：DebugFunction
data class DebugFunction(
    val name: String,
    val desc: String,
    val method: Method,
    val enable: Boolean,
    val target: Any
) {
    fun invoke() {
        method.invoke(target)
    }
}