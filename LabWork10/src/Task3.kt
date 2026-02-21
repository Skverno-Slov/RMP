import kotlinx.coroutines.*
import java.util.*

suspend fun main() = coroutineScope<Unit> {
    try {
        withTimeout(10000) {
            tryConnectDb()
        }
    }
    catch (_: TimeoutCancellationException){
        println("Превышено время ожидания")
    }
}

suspend fun tryConnectDb(){
    for(i in 1..5){
        delay(3000);
        println("Попытка подключения к БД")
    }

    println("Подключено")
}