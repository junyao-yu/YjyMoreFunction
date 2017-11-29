package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2017/11/29 下午3:12
 * Email：yujunyao@ylb.net
 */

class Account {

    var balance = 100.0

    infix fun add(amount: Double) {
        this.balance = balance + amount
    }

}