//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    while(true) {
        print("Введите шаг: ")
        val step = readlnOrNull()?.toIntOrNull()
        if (step == null || step <=0 ){
            println("Неверный ввод")
        }
        else{
            WriteTemperature(step)
        }
    }
}

fun WriteTemperature(step:Int = 1){
    val startValue = 100;
    val endValue = -50;
    println("C | F")
    for (i in 100 downTo -50 step step)
    {
        val farengate = i * 1.8 + 32
        println("$i | $farengate")
    }
}