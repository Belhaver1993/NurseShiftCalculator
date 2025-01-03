package pl.jakubgil.calendar.data.holidays

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import pl.jakubgil.calendar.data.holidays.model.Holiday

internal class HolidayFileReader(
    private val json: Json,
    private val context: Context,
) {

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun read(): List<Holiday> = withContext(Dispatchers.IO) {
        return@withContext try {
            json.decodeFromStream<List<Holiday>>(context.assets.open("holidays_2025_2030.json"))
        } catch (e: Exception) {
            Log.e(TAG, "Error loading holidays", e)
            listOf()
        }
    }

    private companion object {
        private const val TAG = "HolidayFileReader"
    }
}