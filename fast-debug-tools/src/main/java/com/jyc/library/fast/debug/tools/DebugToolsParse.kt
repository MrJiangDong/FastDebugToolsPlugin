package com.jyc.library.fast.debug.tools

import java.lang.reflect.Method


/// @author jyc
/// 创建日期：2022/9/23
/// 描述：DebugToolsParse
class DebugToolsParse {
    fun parse(): MutableList<DebugFunction> {
        val functions = mutableListOf<DebugFunction>()
        val size = DebugToolsManager.getDebugToolsParasClass().size
        for (index in 0 until size) {
            val clazz = DebugToolsManager.getDebugToolsParasClass()[index]
            val target = clazz.getConstructor().newInstance()

            //获取class中的所有方法
            val declaredMethods = target.javaClass.declaredMethods

            for (method in methodsComparator(declaredMethods)) {
                var title = ""
                var desc = ""
                var enable = false
                //返回一个注解实体
                val annotation = method.getAnnotation(FastDebug::class.java)

                if (annotation != null) {
                    title = annotation.name
                    desc = annotation.desc
                    enable = true
                } else {
                    method.isAccessible = true
                    title = method.invoke(target) as String
                }

                val func = DebugFunction(title, desc, method, enable, target)
                functions.add(func)
            }
        }
        return functions
    }

    private fun methodsComparator(declaredMethods: Array<Method>): Array<Method> {
        val sort: Array<Method> = declaredMethods.filter {
            //过滤一批没有添加注解的方法
            it.getAnnotation(FastDebugEnable::class.java) != null
        }.sortedBy {
            it.getAnnotation(FastDebugEnable::class.java)!!.order
        }.toTypedArray()

        return sort
    }
}