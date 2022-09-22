package com.jyc.library.fast.debug.tools


/// @author jyc
/// 创建日期：2021/8/21
/// 描述：FastDebug
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class FastDebug(val name: String, val desc: String)


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class FastDebugEnable(val isEnable: Boolean = true)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class FastToolsFunction