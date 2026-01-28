fun main() {
    print("Введите делимое: ")
    val divided = readlnOrNull()?.toFloatOrNull()
    while (true) {
        print("Введите делитель: ")
        val divider = readlnOrNull()?.toFloatOrNull()
        if (divider == null || divider == 0.toFloat() || divided == null) {
            println("Неверный ввод")
        } else {
            println("$divided / $divider = ${divided/divider}")
        }
    }
}