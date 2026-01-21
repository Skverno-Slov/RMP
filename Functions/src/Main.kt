//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //fun name(param1:Int, param2:String = "Hello", param3:Int) : Int
    hello()
    hello()

    val h = ::hello

    h()
    h()

    name(1, param3 = 7)

    printLines("1", "2", "3", "4")
    val arr = arrayOf("1", "2", "3", "4")
    printLines(*arr)

    val anon = fun (x: Int) = x*x
    val lambda1 = { println() }
    var lambda2 = { x:Int -> println(x) }

    var c = creteCounter()
    c()
    c()
    c()
}

fun hello(){
    println("Hello")
}

fun name(param1:Int, param2:String = "Hello", param3:Int) : Int{
    return 1
}

fun printLines(vararg lines:String){
    for (line in lines ){
        println(line)
    }
}

fun selectAction(op:String) : (Int, Int)->Int {
    return when(op){
        "+" -> ::sum
        "-" -> ::sub
        else -> {_,_->0}
    }
}

fun sum(a:Int, b:Int) = a + b
fun sub(a:Int, b:Int) = a - b

fun creteCounter(): () -> Unit{
    var n = 0 //Сохраняется 

    fun counter(){
        println(n++)
    }

    return ::counter
}