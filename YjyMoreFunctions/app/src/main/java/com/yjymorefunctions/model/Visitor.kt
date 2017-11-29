package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2017/11/29 上午11:31
 * Email：yujunyao@ylb.net
 */

class Visitor(val map: Map<String, Any?>) {

    val name: String by map
    val age: Int by map

}
//或者
//class Visitor(var map: MutableMap<String, Any?>) {
//
//    var name: String by map
//    var age: String by map
//
//}