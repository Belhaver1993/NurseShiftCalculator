package pl.jakubgil.calendar.data.holidays

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import pl.jakubgil.calendar.data.holidays.model.Holiday

internal class HolidayFileReaderImpl(private val json: Json) : HolidayFileReader {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun read(): List<Holiday> = withContext(Dispatchers.IO) {
        return@withContext try {
            val holidayFile = FileSystem.RESOURCES.source("assets/holidays_2025_2030.json".toPath())
            json.decodeFromStream<List<Holiday>>(holidayFile.buffer().inputStream())
        } catch (e: Exception) {
            listOf()
        }
    }
}
