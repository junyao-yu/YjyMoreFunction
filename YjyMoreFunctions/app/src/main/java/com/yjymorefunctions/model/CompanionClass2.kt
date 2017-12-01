package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2017/12/1 上午10:56
 * Email：yujunyao@ylb.net
 */

class CompanionClass2 {

    companion object {
        const val name = "yujunyao"//const只能修饰val
        var x = 100
        const val tempName = "xxx$name"//const只能凭借const属性
    }

}