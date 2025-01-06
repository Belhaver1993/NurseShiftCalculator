package pl.jakubgil.calendar.data.holidays.model

import kotlinx.serialization.Serializable

@Serializable(with = HolidaySerializer::class)
data class Holiday(val year: Int, val month: Int, val day: Int, val name: String)
