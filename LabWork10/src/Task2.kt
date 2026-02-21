import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.*

suspend fun main() = coroutineScope<Unit> {
    launch {
        printShip()
    }
    launch {
        printCat()
    }
}

suspend fun printShip(){
    for (i in 1..500){
        println("Барашек $i")
    }
    delay(1000);
    for (i in 501..1000){
        println("Барашек $i")
    }
}

suspend fun printCat(){
    for (i in 1..500){
        println("Котик $i")
        delay(10);
    }
}