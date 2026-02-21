import kotlinx.coroutines.*
import java.util.*

suspend fun main() = coroutineScope<Unit> {
    val result: Deferred<Int> = async {
        delay(1000)
        val results = listOf(200, 400, 401, 403, 404, 410, 500)
        results.random()
    }

    println(result.await())
}