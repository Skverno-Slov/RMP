import java.util.logging.ConsoleHandler

fun main() {
    val name = "Kotlin"
    var mas = arrayOf("`", "'", "~", "*", "*", "%", "#", "@")
    println("Hello, " + name + "!")

    while (true) {
        for (i in 0..<7) {
            var temp = mas[i]
            mas[i] = mas[i + 1]
            mas[i + 1] = temp
        }
        Thread.sleep(100)
        println()
        for (m in mas) {
            print(m)
        }
    }

    var number = if (mas[1] == readln()) {  //без var number
        print("sijdiaojds")
        1
    } else if (mas[2] == "d") {
        2
    } else {
        3
    }

    var j = when (number) { //без var j
        1 -> {
            print("")
            "hsajdiaujhd"
        }

        2 -> println()
        3, 4 -> println()
        in 5..8 -> println()
        else -> {
            println()
            println()
        }
    }

    when{
        (j == 0) -> println()
        (j == 1) -> println()
    }

    mas.reverse() //развернуть
    mas.shuffle() //перемешать
    mas.random() //случайный элемент
    mas.sort() //сортироовка

    var table : Array<Array<Int>> //ассив массивов
}
