package challenge.ihaus.parsa.util

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import java.io.IOException
import java.io.InputStreamReader

object JsonUtil {
    fun getJsonFileContent(fileName: String): String {
        try {
            val inputStream = getInstrumentation().context.resources.assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}