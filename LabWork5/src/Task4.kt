fun main(){
        print("Введите сумму покупки: ")
        val sum = readlnOrNull()?.toFloatOrNull()
        print("Введите внесённую сумму: ")
        val money = readlnOrNull()?.toFloatOrNull()
        if (sum == null || sum <= 0 || money == null || money <= 0) {
            println("Неверный ввод")
        }
        else {
            GetCheck(sum, money)
        }
    }

fun GetCheck(sum:Float, money:Float){
    var currentMoney = money
    var remainder:Float = sum
    do{
        remainder -= currentMoney
        if (remainder > 0){
            println("Не хватает: $remainder")
            println("Дай ещё:")
            currentMoney = readln().toFloat()
        }
    }while (remainder > currentMoney)
    println("Спасибо!")
    if (remainder.equals(0)){
        println("Сдачи нет")
    }
    if (remainder < 0){
        println("Сдача: ${-remainder}")
    }
}