package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2017/11/30 下午3:01
 * Email：yujunyao@ylb.net
 */

data class OperatorSmybol(var age: Int) {

    operator fun unaryPlus(): OperatorSmybol {//+a
        age = age.plus(100)
        return this
    }

    operator fun plus(oo: OperatorSmybol): OperatorSmybol {//a+b
        age += oo.age
        return this
    }

    operator fun plusAssign(oo: OperatorSmybol) {//+=
        age += oo.age
    }


}