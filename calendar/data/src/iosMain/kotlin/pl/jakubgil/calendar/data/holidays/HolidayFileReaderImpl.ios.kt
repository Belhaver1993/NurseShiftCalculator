package pl.jakubgil.calendar.data.holidays

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import pl.jakubgil.calendar.data.holidays.model.Holiday
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringWithContentsOfFile

internal class HolidayFileReaderImpl(
    private val json: Json,
) : HolidayFileReader {

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun read(): List<Holiday> = withContext(Dispatchers.IO) {
        return@withContext try {
            val holidayFile = NSBundle.mainBundle.resourcePath + "/compose-resources/" + "assets/holidays_2025_2030.json"
            memScoped {
                val holidayFileContent = NSString.stringWithContentsOfFile(holidayFile, encoding = NSUTF8StringEncoding, error = null) ?: ""
                json.decodeFromString<List<Holiday>>(holidayFileContent)
            }
        } catch (e: Exception) {
            listOf()
        }
    }

}
