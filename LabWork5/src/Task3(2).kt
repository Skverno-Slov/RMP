fun main() {
    while (true) {
        print("Введите сумму вклада: ")
        val money = readlnOrNull()?.toFloatOrNull()
        print("Введите процент: ")
        val percent = readlnOrNull()?.toIntOrNull()
        if (percent == null || percent <= 0 || money == null || money <= 0) {
            println("Неверный ввод")
        } else if (money >= 1000000){
            println("Вы уже миллионер")
        }
        else {
            println("Вы станете миллионером через: ${BecomMillionaire(money, percent)}")
        }
    }
}

fun BecomMillionaire(money:Float, percent:Int) : Int{
    var sum = money
    var year = 0
    while (sum < 1000000){
        val toAdd = sum / 100 * percent
        sum += toAdd
        year++
    }
    return year
}