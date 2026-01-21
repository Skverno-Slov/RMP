//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val dataIncorrectMessage = "Некорректный ввод данных"

    //task1
    println("Введите первое число: ")
    val number1 : Double? = readlnOrNull()?.toDoubleOrNull()
    println("Введите второе число: ")
    val number2 : Double? = readlnOrNull()?.toDoubleOrNull()

    if(number1 == null || number2 == null){
        println(dataIncorrectMessage)
    }
    else{
        WriteMathResult(number1, number2)
    }

    //task2
    println("Введите ваше имя: ")
    val name = readlnOrNull()

    println("Введите ваш рост в см: ")
    val height : Double? = readlnOrNull()?.toDoubleOrNull()

    println("Введите ваш вес: ")
    val weight : Int? = readlnOrNull()?.toIntOrNull()


    if (height == null || weight == null || name == null){
        println(dataIncorrectMessage)
    }
    else{
        WriteIwb(name, weight, height)
    }

    //task3
    println("Введите количество секунд: ")
    val seconds : Int? = readlnOrNull()?.toIntOrNull()

    if (seconds == null || seconds <= 0) {
        println(dataIncorrectMessage)
    }
    else {
        WriteTimer(seconds)
    }

    //task4
    println("Введите год: ")
    val year : Int? = readlnOrNull()?.toIntOrNull()

    if(year == null){
        println(dataIncorrectMessage)
    }
    else{
        println(IsLeap(year))
    }
}

private fun IsLeap(yaer: Int): Boolean {
    return (yaer % 4 == 0 && yaer % 100 != 0 || yaer % 400 == 0)
}

private fun WriteTimer(totalSeconds: Int) {
    val hourDivider = 3600
    val minuteDivider = 60
    var seconds = totalSeconds.rem(86400)
    val hours = seconds.div(hourDivider)
    seconds = seconds.minus(hours.times(hourDivider))
    val minutes = seconds.div(minuteDivider)
    seconds = seconds.minus(minutes.times(minuteDivider))

    println("$hours:$minutes:$seconds")
}

private fun WriteIwb(name: String, weight: Int, height: Double) {
    val meterMultiplayer = 100
    val heightMeter = height.div(meterMultiplayer)
    println("$name ваш ИМТ = ${weight.div(heightMeter.times(heightMeter))}")
}

private fun WriteMathResult(number1: Double, number2: Double) {
    println("при a = $number1, и b = $number2")

    println("$number1 + $number2 = ${number1.plus(number2)} ")
    println("$number1 - $number2 = ${number1.minus(number2)}")
    println("$number1 * $number2 = ${number1.times(number2)}")
    println("$number1 / $number2 = ${number1.div(number2)}")
    println("Остаток: ${number1.mod(number2)}")
}
