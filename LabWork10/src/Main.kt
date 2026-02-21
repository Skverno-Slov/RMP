import kotlinx.coroutines.*
import java.util.Scanner
import kotlin.coroutines.*

suspend fun main() = coroutineScope<Unit> {
    val scanner = Scanner(System.`in`)

    val job = launch {
        try{
            loadFile()
            }
        catch (_: CancellationException)
            {
                println("Загрузка отменена")
            }
        }
    launch {
        while (isActive){
            val text = scanner.nextLine()
            if(text == "cancel"){
                job.cancel()
                break
            }
        }
    }

    job.join()
}

suspend fun loadFile(){
    for(i in 1..30){
        delay(3000);
        println("Загрузка файла $i")
    }

    println("Загрузка завершена")
}
