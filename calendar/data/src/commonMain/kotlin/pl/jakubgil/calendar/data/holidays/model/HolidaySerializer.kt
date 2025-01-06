package pl.jakubgil.calendar.data.holidays.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class HolidaySerializer : KSerializer<Holiday> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Holiday") {
        element<Int>("year")
        element<Int>("month")
        element<Int>("day")
        element<String>("name")
    }

    override fun serialize(encoder: Encoder, value: Holiday) = throw UnsupportedOperationException(
        "Serialization is not supported, only deserialization",
    )

    override fun deserialize(decoder: Decoder): Holiday {
        val jsonDecoder = decoder as JsonDecoder
        val list = jsonDecoder.decodeJsonElement()

        val date = list.jsonObject["startDate"]?.jsonPrimitive?.content?.split("-")
        if (date == null || date.size < 3) {
            throw IllegalArgumentException("Invalid date format")
        }
        val year = date[0].toInt()
        val month = date[1].toInt()
        val day = date[2].toInt()

        val name = list.jsonObject["name"]?.jsonArray?.first()?.jsonObject?.get("text")?.jsonPrimitive?.content

        return Holiday(year, month, day, name ?: "")
    }
}
