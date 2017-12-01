package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2017/12/1 上午10:52
 * Email：yujunyao@ylb.net
 */

class CompanionClass1 {
    //companion object 类似于java的static
    companion object Factory {
        fun create() {
            println("companion")
        }
    }

//    object Factory {
//        fun create() {
//            println("companion")
//        }
//    }

}