fun main() {
    print("\t")
    for (n in 0 .. 10){
        print("$n    \t")
    }
    println()
    for(i in 10 .. 90 step 10){
        print("$i \t")
        for (j in 0..10) {
            val cellValue = i + j
            print("${cellValue * cellValue}  \t")
        }
        println()
    }
}