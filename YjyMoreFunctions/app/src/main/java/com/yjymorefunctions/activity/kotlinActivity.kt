package com.yjymorefunctions.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yjymorefunctions.R
import com.yjymorefunctions.model.*
import kotlinx.android.synthetic.main.activity_kotlin.*


class kotlinActivity : AppCompatActivity() {

    val temp: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        main_tv.text = "Hello Kotlin!!!"

        Thread(
               {
            Log.e("sdfdsfds", "sfsdfsdfsd")
            }

        ).start()


//        var aaa = TestKotlinBean("", 29, "")
//        var bbb = TestKotlinBean(29, "")

        //函数扩展
        var list = mutableListOf(1, 2, 3)
        list.swap(0, 1)//0和1的位置交换了

        for (aa in list) {
            Log.e("--->", "${aa}")
        }
        list.exchange(1, 2)
        for (aa in list) {
            Log.e("--->", "${aa}")
        }
        Log.e("lastIndex--->", "${list.lastIndex}")

//        var tempBean: TempBean = TempBean("", "")

        var a = A()
        Log.e("--->", a.lastName)

        Log.e("--->", "${a.num}")

        a.num = 100
        Log.e("--->", "${a.num}")

        //类委托
        var b = Baseimpl(10)
        Printer(b).print()

        //属性委托
        val e = Example()
        Log.e("property-->", e.property)
        e.property = "new"
        Log.e("property-->", e.property)

        //延迟属性->感觉属性值为null的时候进一次，只有直接返回值
        Log.e("lazy--->", lazyValue)
        Log.e("lazy--->", lazyValue)


        //可观察属性
        val guest = Guest()
        guest.age = 1
        guest.age = 2

        guest.gender = 2
        Log.e("guest-->", guest.gender.toString())
        guest.gender = 1
        Log.e("guest-->", guest.gender.toString())
        guest.gender = 3
        Log.e("guest-->", guest.gender.toString())

        //map映射委托
        val visitor = Visitor(mapOf("name" to "yujunyao", "age" to 25))
        Log.e("visitor-->", visitor.name)
        Log.e("visitor-->", "${visitor.age}岁")


        //run用法1
        //功能：调用run函数块。返回值为函数块最后一行，或者指定return表达式。
        var aa = run {
            Log.e("run--->", "aa")
            //return@run 3
            //或者
            3
        }
        println(aa)

        //run用法2
        //功能：调用某对象的run函数，在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。
        var bbb = "bbb".run {
            println(this)
            5
        }
        println(bbb)

        temp.run {//temp为空但是也能执行
            println("run--->null")
        }


        //apply
        //功能：调用某对象的apply函数，在函数块内可以通过 this 指代该对象。返回值为该对象自己。
        var ccc = "ccc".apply {
            println(this)
        }
        println(ccc)

        temp.apply {//temp为空但是也能执行
            println("apply--->null")
        }

        //let
        //功能：调用某对象的let函数，则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为函数块的最后一行或指定return表达式。
        var ddd = "ddd".let {
            println(it)
            6
        }
        println(ddd)

        temp.let {//temp为空但是也能执行
            println("let--->null")
        }

        //also
        //功能：调用某对象的also函数，则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为该对象自己。
        var eee = "eee".also {
            println(it)
        }
        println(eee)

        temp.also {//temp为空但是也能执行
            println("also--->null")
        }

        //with
        //功能：with函数和前面的几个函数使用方式略有不同，因为它不是以扩展的形式存在的。它是将某对象作为函数的参数，在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。
        var fff = with("fff") {
            println(this)
            8
        }
        println(fff)


        //infix->扩展函数
        var number = 100 add 200
        println("${number}")

        //infix->成员函数
        val account = Account()
        account add 200.0
        println("${account.balance}")

    }

    /**
     * infix函数需要几个条件:
    - 只有一个参数
    - 在方法前必须加infix关键字
    - 必须是成员方法或者扩展方法
     */
    infix fun Int.add(x: Int): Int {
        return this + x
    }

    /**
     * lazy()只能有在val类型上，而lateinit只能用在var类型上面
     * lateinit不能用在可空的属性上面
     * lateinit可以在任何位置初始化并且可以初始化多次。而lazy在第一次被调用时就被初始化，想要被改变只能重新定义
     * lateinit 有支持（反向）域（Backing Fields）
     */
    lateinit var aaa: String
    val lazyValue: String by lazy {
        Log.e("lazy", "just run when first being used")
        "value"
    }


    //函数扩展
    fun MutableList<Int>.swap(x: Int, y: Int) {
        var temp = this[x]
        this[x] = this[y]
        this[y] = temp
    }

    //函数扩展泛型
    fun <T> MutableList<T>.exchange(x: Int, y: Int) {
        var temp = this[x]
        this[x] = this[y]
        this[y] = temp
    }

    val <T> List<T>.lastIndex: Int get() = size - 1


}
